package br.com.goldenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_produto.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.adapter_produto.*

class ProdutoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        val produto = intent.getSerializableExtra("produto")

        supportActionBar?.title = "Produto Golden Bear"
        mensagemPaginaProduto.text = "Bem-vindo ao produto da Golden Bear"
        Toast.makeText(this, "$produto.", Toast.LENGTH_LONG).show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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