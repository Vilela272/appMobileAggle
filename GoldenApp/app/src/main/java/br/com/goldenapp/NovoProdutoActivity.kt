package br.com.goldenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_novo_produto.*
import kotlinx.android.synthetic.main.toolbar.*

class NovoProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_produto)
        supportActionBar?.title = "Adicionar novo produto"

        botaoSalvar.setOnClickListener {
            val produto = Produto()
            produto.nome = nomeDoProduto.text.toString()
            produto.proprietario = proprietario.text.toString()
            produto.foto = foto.text.toString()
            produto.descricao = descricao.text.toString()

            taskAtualizar(produto)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun taskAtualizar(produto: Produto){

        Thread {
            ProdutoService.saveProduto(produto)
            runOnUiThread {
                finish()
            }
        }.start()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, TelaInicialActivity::class.java)
                Toast.makeText(this, "Clicou no botão de voltar", Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}