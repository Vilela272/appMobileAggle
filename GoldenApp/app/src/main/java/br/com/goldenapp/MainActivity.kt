package br.com.goldenapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        campoUsuario.setText(Prefs.getString("lembrarNome"))
        campoSenha.setText(Prefs.getString("lembrarSenha"))
        checkLembrar.isChecked = Prefs.getBoolean("lembrar")

        campoImagem.setImageResource(R.drawable.golden_bear_logo)

        buttonLogin.setOnClickListener {
            val usuario = campoUsuario.text.toString()
            val senha = campoSenha.text.toString()
            val remember = checkLembrar.isChecked

            btnLoginLoading.visibility = View.VISIBLE
            buttonLogin.text = ""

            onLogin(usuario, senha) {
                btnLoginLoading.visibility = View.INVISIBLE

                if ((usuario == "aluno") && (senha == "impacta")) {
                    this.acessarTelaInicial()
                    val intent = Intent(context, TelaInicialActivity::class.java)
                    intent.putExtra("nome", usuario)
                    intent.putExtra("numero", 10)
                    startActivity(intent)
                }
                else if ((usuario == "") || (senha == "")) {
                    Toast.makeText(applicationContext, "Usuário e/ou senha em branco", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(applicationContext, "Usuário e/ou senha incorretos", Toast.LENGTH_LONG).show()
                }

                Prefs.setBoolean("lembrar", remember)
                if (checkLembrar.isChecked) {
                    Prefs.setString("lembrarNome", usuario)
                    Prefs.setString("lembrarSenha", senha)
                }
                else {
                    Prefs.setString("lembrarNome", "")
                    Prefs.setString("lembrarSenha", "")
                }
            }
        }
    }

    fun acessarTelaInicial() {
        var intent = Intent(this, TelaInicialActivity::class.java)
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

    fun onLogin(usuario: String, senha: String, callback: (result: Boolean) -> Unit) {
        val url = "https://account.impacta.edu.br/account/enter.php"
        val params = listOf("desidentificacao" to usuario, "dessenha" to senha)

        Fuel.post(url, params)
            .responseJson { request, response, result ->
                result.fold(success = { json ->
                    callback(json.obj().get("success") == true)
                    btnLoginLoading.visibility = View.INVISIBLE
                }, failure = { error ->
                    Toast.makeText(applicationContext, "Erro inesperado", Toast.LENGTH_SHORT).show()
                    Log.i("RequestResult", error.toString())
                })
            }
    }
}