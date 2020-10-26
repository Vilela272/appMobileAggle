package br.com.goldenapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.menu_lateral_cabecalho.*
import kotlinx.android.synthetic.main.navigation_menu.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var produtos = listOf<Produto>()
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        setSupportActionBar(toolbar)

        this.generic_layout = layoutMenuLateral
        supportActionBar?.title = "Lista de Produtos"
        mensagemInicial.text = "Produtos Golden Bear"
        configuraMenuLateral()

        recyclerProdutos?.layoutManager = LinearLayoutManager(context)
        recyclerProdutos?.itemAnimator = DefaultItemAnimator()
        recyclerProdutos?.setHasFixedSize(true)

    }
    override fun onResume() {
        super.onResume()
        taskProdutos()
    }

    fun taskProdutos() {
        // Código para procurar os produtos
        // que será executado em segundo plano / Thread separada
        Thread{
            this.produtos = ProdutoService.getProdutos(context)
            // atualizar lista
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                recyclerProdutos?.adapter = ProdutosAdapter(this.produtos) {onClickProduto(it)}
            }
        }.start()
    }

    fun onClickProduto(produto: Produto) {

        Toast.makeText(context, "Clicou produto ${produto.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        startActivityForResult(intent, REQUEST_REMOVE)
    }

    // configuraçao do navigation Drawer com a toolbar
    override fun configuraMenuLateral() {
        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, layoutMenuLateral, toolbar, R.string.nav_abrir, R.string.nav_fechar)

        layoutMenuLateral.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView?)?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("SEARCH_TOOLBAR", "toolbar text change: $newText")
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(context, "Buscando: $query", Toast.LENGTH_SHORT).show()
                if(progressBar.visibility == View.VISIBLE) return false
                progressBar.visibility = View.VISIBLE
                Thread(Runnable {
                    Thread.sleep(1000)
                    progressBar.visibility = View.INVISIBLE
                }).start()
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_atualizar -> this.onLoading()
            R.id.action_sair -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            R.id.action_nova -> {
                val intent = Intent(this, NovoProdutoActivity::class.java)
                Toast.makeText(this, "Clicou no botão de adicionar", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun onLoading() {
        if(progressBar.visibility == View.VISIBLE) return
        progressBar.visibility = View.VISIBLE
        Thread(Runnable {
            Thread.sleep(10000)
            progressBar.visibility = View.INVISIBLE
        }).start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE) {
            // atualizar lista de disciplinas
            taskProdutos()
        }
    }
}