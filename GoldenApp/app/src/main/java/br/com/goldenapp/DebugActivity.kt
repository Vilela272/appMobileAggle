package br.com.goldenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

open class DebugActivity : AppCompatActivity() {

    private val TAG = "GoldenApp"
    private val className: String
        get() {
            val nomeDaClasse = javaClass.name
            return nomeDaClasse.substring(nomeDaClasse.lastIndexOf("."))
            // nomeDaClasse = br.com.goldenapp.DebugActivity
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, className + ".onCreate() foi chamado")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, className + ".onStart() chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, className + ".onResume() foi chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, className + ".onStop() foi chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,className + ".onRestart() foi chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, className + ".onDestroy() foi chamado")
    }
}