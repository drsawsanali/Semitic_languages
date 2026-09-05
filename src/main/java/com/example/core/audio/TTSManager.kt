package com.example.core.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.speech.tts.TextToSpeech
import java.util.Locale
import kotlin.math.sin

class SemiticTtsManager(private val context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var isInitialized = false

    init {
        tts = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("ar"))
            isInitialized = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    fun speakArabic(text: String, speed: Float = 1.0f) {
        if (isInitialized) {
            tts?.setSpeechRate(speed)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "semitic_tts_id")
        }
    }

    /**
     * Synthesizes a clean acoustic pitch chime for epigraphic keys and acoustic feedback.
     */
    fun playChime(frequencyHz: Int = 440, durationMs: Int = 250) {
        playFormantTone(frequencyHz.toFloat(), frequencyHz * 1.5f, durationMs)
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }

    /**
     * Synthesizes acoustic formant frequencies for ancient Semitic pitch chimes and vowels
     * (e.g. Proto-Semitic *ā, *ī, *ū, Phoenician *ō).
     */
    fun playFormantTone(f1Hz: Float, f2Hz: Float, durationMs: Int = 400) {
        Thread {
            val sampleRate = 44100
            val numSamples = (sampleRate * (durationMs / 1000.0)).toInt()
            val sample = ShortArray(numSamples)
            val bufferSize = numSamples * 2

            for (i in 0 until numSamples) {
                val t = i.toDouble() / sampleRate
                // Combine F1 and F2 formants with amplitude envelope
                val envelope = when {
                    i < sampleRate * 0.05 -> i / (sampleRate * 0.05) // 50ms attack
                    i > numSamples - sampleRate * 0.05 -> (numSamples - i) / (sampleRate * 0.05) // 50ms decay
                    else -> 1.0
                }
                val wave1 = sin(2.0 * Math.PI * f1Hz * t) * 0.5
                val wave2 = sin(2.0 * Math.PI * f2Hz * t) * 0.4
                val wave = (wave1 + wave2) * envelope * Short.MAX_VALUE * 0.6
                sample[i] = wave.toInt().coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
            }

            val audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(bufferSize)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            audioTrack.write(sample, 0, numSamples)
            audioTrack.play()
            Thread.sleep(durationMs.toLong() + 50)
            audioTrack.release()
        }.start()
    }
}
