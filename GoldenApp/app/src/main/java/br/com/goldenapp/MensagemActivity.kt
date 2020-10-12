package br.com.goldenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forum.*
import kotlinx.android.synthetic.main.activity_forum.layoutMenuLateral
import kotlinx.android.synthetic.main.activity_mensagem.*
import kotlinx.android.synthetic.main.toolbar.*

class MensagemActivity : DebugActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensagem)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.generic_layout = layoutMenuLateral
        supportActionBar?.title = "Menu Mensagens"
        mensagemInicialMensage.text = "Página de mensagens"
        configuraMenuLateral()
    }
}