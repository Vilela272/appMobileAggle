package br.com.goldenapp


import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_produto.*
import kotlinx.android.synthetic.main.toolbar.*

class ProdutoActivity : AppCompatActivity() {

    private val context: Context get() = this
    var produto: Produto? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)

        produto = intent.getSerializableExtra("produto") as Produto

        supportActionBar?.title = produto?.nome
        mensagemPaginaProduto.text = "Bem-vindo ao produto da Golden Bear"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main_produto, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // id do item clicado
        when(item.itemId) {
            // botão up navigation
            android.R.id.home -> {
                val intent = Intent(this, TelaInicialActivity::class.java)
                Toast.makeText(this, "Clicou no botão de voltar", Toast.LENGTH_SHORT).show()
                this.finish()
            }
            // remover o produto no WS
            R.id.action_remover -> {
                // alerta para confirma a remoção
                // só remove se houver confirmação positiva
                AlertDialog.Builder(this)
                    .setTitle(R.string.app_name)
                    .setMessage("Tem certeza que você deseja excluir o produto ?")
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
                    // após remover, voltar para a activity anterior
                    finish()
                }
            }.start()
        }
    }
}