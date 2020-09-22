package br.com.goldenapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        campoImagem.setImageResource(R.drawable.golden_bear_logo)

        // segunda forma: delegar para método
        buttonLogin.setOnClickListener { onClickLogin() }

    }

    fun onClickLogin() {

        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()
        //Toast.makeText(context, "$valorUsuario : $valorSenha", Toast.LENGTH_LONG).show()

        if((valorUsuario == "aluno") && (valorSenha == "impacta")) {
            this.acessarTelaInicial()
            val intent = Intent(context, TelaInicialActivity::class.java)
            intent.putExtra("nome", valorUsuario)
            intent.putExtra("numero", 10)
            startActivity(intent)
        }
        else if((valorUsuario == "") || (valorSenha == "")) {
            Toast.makeText(
                applicationContext,
                "Usuário e/ou senha não podem ser nulos",
                Toast.LENGTH_LONG
            ).show()
        }
        else {
            Toast.makeText(
                applicationContext,
                "Usuário e/ou senha incorretos",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun acessarTelaInicial(){
        startActivity(intent)
        this.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}