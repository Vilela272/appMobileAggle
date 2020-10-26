package br.com.goldenapp

import android.app.Application
import java.lang.IllegalStateException

class GoldenAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: GoldenAppApplication? = null
        fun getInstance(): GoldenAppApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure application no Manifest")
            }
            return appInstance!!
        }
    }

    // chamado quando android terminar processo da aplicação
    override fun onTerminate() {
        super.onTerminate()
    }
}