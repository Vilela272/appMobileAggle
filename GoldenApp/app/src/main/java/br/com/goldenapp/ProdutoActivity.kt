package br.com.goldenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_produto.*
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.adapter_produto.*

class ProdutoActivity : AppCompatActivity() {
    var produto: Produto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        val produto = intent.getSerializableExtra("produto")

        supportActionBar?.title = "Produto Golden Bear"
        mensagemPaginaProduto.text = "Bem-vindo ao produto da Golden Bear"
        Toast.makeText(this, "$produto.", Toast.LENGTH_LONG).show()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_produto, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, TelaInicialActivity::class.java)
                Toast.makeText(this, "Clicou no botão de voltar", Toast.LENGTH_SHORT).show()
                this.finish()
            }
            R.id.action_remover -> {
                // alerta para confirmar a remeção
                // só remove se houver confirmação positiva
                AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Deseja excluir o produto")
                    .setPositiveButton("Sim") {
                            dialog, which ->
                        dialog.dismiss()
                        taskExcluir()
                    }.setNegativeButton("Não") {
                            dialog, which -> dialog.dismiss()
                    }.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun taskExcluir() {
        if (this.produto != null && this.produto is Produto) {
            // Thread para remover o produto
            Thread {
                ProdutoService.delete(this.produto as Produto)
                runOnUiThread {
                    this.finish()
                }
            }.start()
        }
    }
}