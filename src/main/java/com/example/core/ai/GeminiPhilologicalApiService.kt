package com.example.core.ai

import android.util.Log
import com.example.BuildConfig
import com.example.core.model.InscriptionArtifact
import com.example.core.model.InscriptionLinguisticBreakdown
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GeminiApiEndpoint {
    @POST("v1beta/models/{model}:generateContent")
    suspend fun generateContent(
        @Path("model") model: String,
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): Response<GeminiResponse>
}

/**
 * Academic Philological AI Service connecting to Google Gemini API
 * for Semitic linguistics, epigraphy, inscriptions analysis, and comparative studies.
 */
class GeminiPhilologicalApiService {

    companion object {
        private const val TAG = "GeminiPhilologicalApi"
        private const val BASE_URL = "https://generativelanguage.googleapis.com/"

        // Supported models according to skill guidelines:
        const val MODEL_FLASH = "gemini-3.5-flash"
        const val MODEL_PRO = "gemini-3.1-pro-preview"

        private const val SYSTEM_PROMPT = """
أنت خبير فيلولوجي وأستاذ في فقه اللغات السامية المقارنة والإبيغرافيا الأثرية في موسوعة اللغات السامية والأطلس الرقمي (جامعة صنعاء - قسم الآثار).
مهمتك تقديم تحليلات علمية دقيقة وموثقة للنصوص، النقوش، والظواهر اللغوية السامية (السامية الشرقية: الأكادية، البابلية، الآشورية، الإيبلاوية؛ والسامية الشمالية الغربية: الكنعانية، الفينيقية، الأوغاريتية، الآرامية، السريانية، النبطية؛ والعربية الجنوبية القديمة: السبئية، المعينية، القتبانية، الحضرمية؛ والسامية الإثيوبية: الجعزية؛ والعربية الشمالية القديمة).

في كل تحليل أكاديمي:
1. اذكر الأصل والتأصيل اللغوي للجذور (Proto-Semitic Roots).
2. قدم المقابلات المعجمية في الفروع السامية الكبرى (الأكادية، الفينيقية/العبرية، الآرامية/السريانية، السبئية، العربية، والجعزية).
3. بين القوانين والتحولات الصوتية المعنية (مثل التحول الكنعاني ā > ō، قانون بجد كفت، وسقوط النون الساكنة).
4. استخدم التدوين الصوتي الدولي (IPA) والنقحرة اللاتينية الدقيقة عند الحاجة.
5. اربط التحليل بالسياق التاريخي والآثاري للحضارة والموقع.
"""
    }

    private val apiEndpoint: GeminiApiEndpoint by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        retrofit.create(GeminiApiEndpoint::class.java)
    }

    /**
     * Checks if the Gemini API Key is configured in BuildConfig.
     */
    fun isApiKeyConfigured(): Boolean {
        return try {
            val key = BuildConfig.GEMINI_API_KEY
            key.isNotBlank() && key != "MY_GEMINI_API_KEY" && !key.contains("DEFAULT_VALUE")
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Retrieves the Gemini API Key safely.
     */
    private fun getApiKey(): String {
        return try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * General generation function handling requests, network execution, and error handling.
     */
    suspend fun generateAcademicAnalysis(
        prompt: String,
        model: String = MODEL_FLASH
    ): GeminiAnalysisState = withContext(Dispatchers.IO) {
        val apiKey = getApiKey()
        if (!isApiKeyConfigured()) {
            return@withContext GeminiAnalysisState.Error(
                errorAr = "مفتاح Gemini API غير مهيأ. يرجى إضافة GEMINI_API_KEY في لوحة الأسرار (Secrets) في AI Studio لتفعيل التحليل الأكاديمي المباشر.",
                isApiKeyMissing = true
            )
        }

        try {
            val request = GeminiRequest(
                contents = listOf(
                    GeminiContent(
                        parts = listOf(GeminiPart(text = prompt)),
                        role = "user"
                    )
                ),
                systemInstruction = GeminiContent(
                    parts = listOf(GeminiPart(text = SYSTEM_PROMPT)),
                    role = "system"
                ),
                generationConfig = GeminiGenerationConfig(
                    temperature = 0.35f,
                    topP = 0.95f,
                    maxOutputTokens = 3072
                )
            )

            val response = apiEndpoint.generateContent(
                model = model,
                apiKey = apiKey,
                request = request
            )

            if (response.isSuccessful) {
                val body = response.body()
                val candidateText = body?.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
                if (!candidateText.isNullOrBlank()) {
                    GeminiAnalysisState.Success(
                        responseText = candidateText,
                        modelUsed = model
                    )
                } else {
                    GeminiAnalysisState.Error(
                        errorAr = "لم يتم الحصول على إجابة نصية من نموذج الذكاء الاصطناعي."
                    )
                }
            } else {
                val errorMsg = response.errorBody()?.string() ?: "كود الخطأ: ${response.code()}"
                Log.e(TAG, "Gemini API Error: $errorMsg")
                GeminiAnalysisState.Error(
                    errorAr = "فشل الاتصال بـ Gemini API: ${response.message()} (رمز: ${response.code()})"
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during Gemini call", e)
            GeminiAnalysisState.Error(
                errorAr = "حدث خطأ أثناء معالجة الطلب: ${e.localizedMessage ?: e.message}"
            )
        }
    }

    /**
     * Academic Philological Inscription Analysis
     */
    suspend fun analyzeInscription(
        titleAr: String,
        originalScript: String,
        scriptType: String,
        transliteration: String,
        translationAr: String,
        discoverySite: String
    ): GeminiAnalysisState {
        val prompt = """
قدم تحليلاً فيلولوجياً وإبيغرافياً مفصلاً للنقش الأثري التالي:
- عنوان النقش: $titleAr
- موقع الاكتشاف والحضارة: $discoverySite
- نوع الخط والكتابة: $scriptType
- النص الأصلي بالرموز: $originalScript
- النقحرة الصوتية اللاتينية: $transliteration
- الترجمة العربية: $translationAr

المطلوب تفصيلاً:
1. قراءة نحوية وصرفية تفكك صيغ الجمل والأفعال وحالات الإعراب أو الإعراب التقديري.
2. جدول الجذور السامية المقارنة (Semitic Roots) لأبرز 4 كلمات في النقش ومقابلاتها في (الأكادية، الفينيقية، الآرامية، السبئية، والعربية).
3. أبرز القوانين الفونولوجية والصوتية البادية في رسم النقش.
4. الدلالة الحضارية والتاريخية ومقارنتها بنقوش أخرى من نفس العصر.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * In-depth Philological Analysis of Keyboard Input / Transcribed Text
     */
    suspend fun analyzeTranscribedText(
        text: String,
        scriptName: String
    ): GeminiAnalysisState {
        val prompt = """
حلل النص السامي التالي المكتوب بخط ($scriptName):
النص:
$text

المطلوب:
1. التعرف على الحروف والرموز وتقديم النقحرة الصوتية اللاتينية الدقيقة (Transliteration).
2. استخراج الجذور السامية المحتملة للكلمات.
3. التفسير الدلالي والنحوي المقدر للنص.
4. المقابلات في اللغات السامية الشقيقة (مثل العربية، السريانية، أو الأكادية).
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * Specialized Linguistic and Philological Aspect Analysis for an Inscription
     */
    suspend fun analyzeInscriptionLinguisticAspect(
        titleAr: String,
        originalScript: String,
        scriptType: String,
        transliteration: String,
        translationAr: String,
        aspectTitle: String,
        specificPrompt: String
    ): GeminiAnalysisState {
        val prompt = """
أنت خبير وأستاذ كرسي في اللسانيات وفقه اللغات السامية وعلم الإبيغرافيا والآثار الشرقية.
المطلوب تقديم تحليل أكاديمي دقيق وتخصصي للنقش الأثري التالي:
- اسم النقش: $titleAr
- الخط والتدوين: $scriptType
- النص الأصلي بالرموز السامية: $originalScript
- النقحرة الصوتية اللاتينية: $transliteration
- الترجمة العربية: $translationAr

المحور التحليلي المطلوب: [$aspectTitle]
$specificPrompt

يرجى مراعاة ما يلي في التحليل:
1. استخدام المصطلحات الفيلولوجية الأكاديمية (مثل: IPA، الصوائت، الجذوع، النحيزة، الباليوغرافيا).
2. استعراض المقارنات مع اللغات السامية الشقيقة (الأكادية، الكنعانية/الفينيقية، العبرية، الآرامية، العربية، المسندية، والجعزية).
3. بيان القوانين الصوتية الحاكمة (Sound Shifts) وتطور الجذور من السامية الأم (Proto-Semitic).
4. تنظيم المخرجات بنقاط وعناوين واضحة وجداول نصية عند الحاجة.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * Deep-dive analysis for a single word/token in an inscription
     */
    suspend fun analyzeSpecificToken(
        inscriptionTitle: String,
        tokenOriginal: String,
        transliteration: String,
        root: String,
        contextSentence: String
    ): GeminiAnalysisState {
        val prompt = """
قدم تحليلاً فيلولوجياً معمقاً للكلمة التالية المقتبسة من ($inscriptionTitle):
- الكلمة بالخط الأصلي: $tokenOriginal
- النقحرة اللاتينية: $transliteration
- الجذر السامي المقدر: $root
- السياق في النقش: $contextSentence

المطلوب:
1. الأصل والتأثيل في السامية الأم (Proto-Semitic Reconstructed Form *...).
2. التحليل الصرفي (الوزن، الصيغة، الزوائد، الضمائر المتصلة، وحالة الإضافة أو الإعراب).
3. جدول مقارن يوضح نطق الكلمة ومعناها في:
   - العربية الفصحى
   - الأكادية (المسمارية)
   - الفينيقية / الكنعانية
   - العبرية القديمة
   - الآرامية / السريانية
   - السبئية (المسند)
   - الجعزية (الإثيوبية)
4. أي تحولات صوتية طرأت على الصوامت أو الصوائت في هذه الكلمة.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * Detailed Academic Explanation of a Semitic Language
     */
    suspend fun explainLanguage(
        languageNameAr: String,
        branchAr: String,
        phonologicalSystem: String,
        scriptSystem: String,
        period: String
    ): GeminiAnalysisState {
        val prompt = """
قدم دراسة أكاديمية موسوعية موجزة ومكثفة عن:
- اللغة السامية: $languageNameAr
- الفرع والشعبة: $branchAr
- الحقبة التاريخية: $period
- النظام الخطي والكتابي: $scriptSystem
- النظام الصوتي والفونولوجي: $phonologicalSystem

المطلوب:
1. موقع هذه اللغة في شجرة اللغات السامية ونقاط الاتصال والانفصال مع السامية الأم.
2. السمات المورفولوجية والنحوية الفارقة (مثل أداة التعريف، علامة الجمع، أوزان الأفعال، وحالة التنوين/الميمية).
3. أهم الأرشيفات والنقوش المكتشفة التي وثقت هذه اللغة.
4. أثر هذه اللغة وميراثها في اللغات السامية اللاحقة.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * Detailed Academic Explanation of a Semitic Civilization
     */
    suspend fun explainCivilization(
        civilizationNameAr: String,
        capitalCity: String,
        flourishedPeriod: String,
        geographicCore: String,
        rulers: List<String>,
        deities: List<String>,
        tradeRoutes: String,
        achievements: String
    ): GeminiAnalysisState {
        val prompt = """
قدم دراسة تاريخية وأثرية وأكاديمية متعمقة عن الحضارة والمملكة السامية:
- الحضارة: $civilizationNameAr
- العاصمة التاريخية: $capitalCity
- فترة الازدهار والحقبة: $flourishedPeriod
- النواة الجغرافية والامتداد: $geographicCore
- أشهر الحكام والملوك: ${rulers.joinToString("، ")}
- البانثيون ومجمع الآلهة: ${deities.joinToString("، ")}
- المسالك والشبكات التجارية: $tradeRoutes
- الإنجازات الحضارية والتشريعية: $achievements

المطلوب:
1. التطور التاريخي والسياسي لنشأة وازدهار هذه الحضارة وعلاقاتها بالقوى المجاورة.
2. البنية الاجتماعية والاقتصادية والتشريعية ونظم الحكم والقضاء.
3. التقاليد الدينية والطقوسية ومكانة الآلهة الرئيسية في البانثيون.
4. الإسهام الإبيغرافي والأدبي والنقشي للحضارة في التراث السامي المقارن.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * Comparative Etymology / Root Study
     */
    suspend fun compareRoots(
        root: String,
        meaning: String
    ): GeminiAnalysisState {
        val prompt = """
أجرِ دراسة تأصيلية مقارنة (Comparative Semitic Etymology) للجذر السامي:
- الجذر: *$root*
- المعنى العام: $meaning

المطلوب:
1. إعادة بناء الصيغة في السامية الأم (Proto-Semitic).
2. تتبع تطور الجذر ودلالاته الصوتية والصرفية عبر الفروع:
   - السامية الشرقية (الأكادية/البابلية)
   - السامية الشمالية الغربية (الأوغاريتية، الفينيقية، العبرية، والآرامية)
   - السامية الجنوبية القديمة (السبئية والمعينية)
   - السامية الجنوبية الحديثة (المهرية والسقطرية)
   - السامية الإثيوبية (الجعزية والأمهرية)
   - العربية الفصحى
3. التحولات الفونولوجية التي طرأت على أصوات الجذر في كل لغة.
"""
        return generateAcademicAnalysis(prompt, MODEL_PRO)
    }

    /**
     * Side-by-side philological and linguistic comparison of two Semitic inscriptions using Gemini AI.
     * Analyzes grammatical, morphological, semantic, and phonological differences.
     */
    suspend fun compareInscriptionsLinguistically(
        first: InscriptionArtifact,
        firstBreakdown: InscriptionLinguisticBreakdown?,
        second: InscriptionArtifact,
        secondBreakdown: InscriptionLinguisticBreakdown?
    ): GeminiAnalysisState {
        val firstTokensSample = firstBreakdown?.tokens?.take(5)?.joinToString("\n") { 
            "  * ${it.tokenOriginal} (${it.transliteration}) - جذر: ${it.root} - دور: ${it.grammaticalRoleAr} - دلالة: ${it.meaningAr}" 
        } ?: "غير متوفر"

        val secondTokensSample = secondBreakdown?.tokens?.take(5)?.joinToString("\n") { 
            "  * ${it.tokenOriginal} (${it.transliteration}) - جذر: ${it.root} - دور: ${it.grammaticalRoleAr} - دلالة: ${it.meaningAr}" 
        } ?: "غير متوفر"

        val firstPhonologySample = firstBreakdown?.phonologicalLaws?.take(3)?.joinToString("; ") {
            "${it.ruleTitleAr} (${it.formula})"
        } ?: "حسب النص"

        val secondPhonologySample = secondBreakdown?.phonologicalLaws?.take(3)?.joinToString("; ") {
            "${it.ruleTitleAr} (${it.formula})"
        } ?: "حسب النص"

        val prompt = """
أنت عالم لغويات سامية مقارنة وإبيغرافيا أثرية في موسوعة اللغات السامية. قم بإجراء دراسة ومقارنة لغوية وفيلولوجية معمقة جنباً إلى جنب بين هذين النقشين الساميين:

═══ [النقش الأول] ═══
- الاسم: ${first.titleAr} (${first.titleEn})
- الموقع والتأريخ: ${first.discoveryLocation} (${first.period} / ${first.dateCentury})
- الخط ونظام التدوين: ${first.scriptType.titleAr}
- النص الأصلي:
${first.scriptTextOriginal}
- النقحرة الصوتية اللاتينية:
${first.transliteration}
- الترجمة العربية:
${first.translationAr}
- القوانين الصوتية المرصودة: $firstPhonologySample
- نماذج من تفكيك المفردات:
$firstTokensSample

═══ [النقش الثاني] ═══
- الاسم: ${second.titleAr} (${second.titleEn})
- الموقع والتأريخ: ${second.discoveryLocation} (${second.period} / ${second.dateCentury})
- الخط ونظام التدوين: ${second.scriptType.titleAr}
- النص الأصلي:
${second.scriptTextOriginal}
- النقحرة الصوتية اللاتينية:
${second.transliteration}
- الترجمة العربية:
${second.translationAr}
- القوانين الصوتية المرصودة: $secondPhonologySample
- نماذج من تفكيك المفردات:
$secondTokensSample

المطلوب تفصيلاً في هذه المقارنة الفيلولوجية المزدوجة:
1. الفروق النحوية والتركيبية (Grammatical & Syntactic Differences):
   - مقارنة رتبة وبناء الجملة (Word Order: الفعل والفاعل والمفعول به).
   - صياغة الأفعال (الأوزان والجذوع كالمجرد G/Qal، التكثير D/Piel، والسببي Š/H/C/Aph'el).
   - تصريف الأسماء وعلامات الإعراب أو سقوطها وحالات التنوين/الميمية (Mimation/Nunation).
   - أدوات الربط (مثل واو العطف التتابعية السردية، وأدوات الموصول والتعريف).

2. الفروق الدلالية والمعجمية (Semantic & Lexical Differences):
   - تحليل الجذور المشتركة (Cognate Roots) في النصين ومقارنة تطور دلالاتها.
   - الفروق الدلالية في المعجم الملكي، الديني، العسكري، أو التشريعي المستخدم.
   - الكلمات الدخيلة أو التأثيرات المتبادلة بين اللهجات.

3. التباينات الصوتية والفونولوجية (Phonological Sound Shifts):
   - كيف يعكس كل نقش حفظ أو دمج أصوات السامية الأم (Proto-Semitic phonemes).
   - التحولات الصوتية البارزة (مثل الصوائت الطويلة، انكماش المزدوجات، أصوات الحلق والإطباق).

4. الخلاصة الإبيغرافية والأثرية:
   - أوجه التقارب والاختلاف الباليوغرافي في شكل الحروف وأسلوب النقش.
   - خلاصة علمية لموقع النقشين في التطور التاريخي للأسرة اللغوية السامية.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }

    /**
     * Specialized interactive academic definition for a Semitic linguistic term or word
     * clicked within textual contexts.
     */
    suspend fun defineSemiticTerm(
        term: String,
        contextSentence: String? = null,
        languageOrBranch: String? = null
    ): GeminiAnalysisState {
        val contextInfo = if (!contextSentence.isNullOrBlank()) {
            "\n- سياق ورود الكلمة في النص الأكاديمي: \"$contextSentence\""
        } else ""
        val branchInfo = if (!languageOrBranch.isNullOrBlank()) {
            "\n- الفرع أو اللغة المرتبطة: $languageOrBranch"
        } else ""

        val prompt = """
بصفتك أستاذاً في فقه اللغات السامية والإبيغرافيا المقارنة، يُرجى تقديم تعريف وتحقيق أكاديمي فيلولوجي موسوعي للمصطلح أو الكلمة السامية التالية:

المصطلح / الكلمة المستعلم عنها: «$term»$branchInfo$contextInfo

المطلوب تفصيلاً وبأسلوب علمي رصين ومنظم:
1. التعريف الأكاديمي الدقيق (Academic Philological Definition):
   - ما هو هذا المصطلح/الكلمة في سياق اللسانيات السامية؟
   - الحقل المعرفي (علم الأصوات، الصرف والمورفولوجيا، النحو والتركيب، الإبيغرافيا والباليوغرافيا، المعاجم والتأصيل).

2. التأصيل اللغوي والجذر في السامية الأم (Proto-Semitic Root & Etymology):
   - الجذر الثلاثي أو الثنائي المفترض في السامية الأم (*).
   - المعنى الأصلي البدائي وتطوره الدلالي.

3. المقابلات المعجمية المقارنة عبر الفروع السامية (Comparative Semitic Cognates):
   - جدول أو نقاط توضح مقابل الكلمة/المصطلح في: الأكادية، الأوغاريتية/الكنعانية، الآرامية/السريانية، السبئية المسندية، العربية الفصحى، والجعزية الإثيوبية، مع التدوين الصوتي الدولي (IPA) عند الإمكان.

4. القوانين والظواهر الصوتية أو الصرفية المقترنة (Sound Shifts & Rules):
   - أي قوانين صوتية تنطبق عليها (مثل التحول الكنعاني، قانون بجد كفت، سقوط النون، انكماش المزدوجات، الميمية/النونية).

5. الشواهد الإبيغرافية الأثرية (Epigraphic Attestations):
   - أين ورد هذا المصطلح أو مشتقاته في النقوش والمسلات القديمة (مثل مسلة ميشع، شريعة حمورابي، نقوش جبيل، نقوش مأرب، أو ألواح رأس الشمرا)؟

6. خلاصة بحثية موجزة للباحثين والطلاب.
"""
        return generateAcademicAnalysis(prompt, MODEL_FLASH)
    }
}
