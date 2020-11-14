package br.com.goldenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_configuracao.*
import kotlinx.android.synthetic.main.toolbar.*

class ConfiguracaoActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.generic_layout = layoutMenuLateral
        supportActionBar?.title = "Menu Configurações"
        mensagemInicialConfiguracoes.text = "Página de Configurações"
        configuraMenuLateral()
    }
}