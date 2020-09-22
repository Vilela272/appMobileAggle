package br.com.goldenapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args = intent.extras

        // recuperar o parâmetro do tipo String
        val nome = args?.getString("nome")

        // recuperar parâmetro simplificado
        val numero = args?.getString("nome", 0.toString())

        Toast.makeText(
            this,
            "Usuário: $nome",
            Toast.LENGTH_LONG
        ).show()

        Toast.makeText(
            context,
            "Usuário: $numero",
            Toast.LENGTH_LONG
        ).show()

        botaoSair.setOnClickListener {cliqueSair()}

        // colocar toolbar
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Produtos Golden"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do GoldenApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }


    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // id do item clicado
        val id = item?.itemId

        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}