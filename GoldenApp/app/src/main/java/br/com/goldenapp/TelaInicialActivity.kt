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
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_tela_inicial.*
import kotlinx.android.synthetic.main.toolbar.*

class TelaInicialActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)
        setSupportActionBar(toolbar)

        btnCamisas.setOnClickListener { openBranchActivity("Camisas") }
        btnCalcas.setOnClickListener { openBranchActivity("Calcas") }
        btnCordoes.setOnClickListener { openBranchActivity("Cordoes") }
        btnAcessorios.setOnClickListener { openBranchActivity("Acessorios") }
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
            R.id.action_config -> {
                var intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                this.onLoading()
            }
            R.id.action_sair -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()
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

    fun openBranchActivity(branch: String) {
        var intent = Intent(this, BranchActivity::class.java)
        intent.putExtra("branch", branch)
        startActivity(intent)
    }
}