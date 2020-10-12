package br.com.goldenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.navigation_menu.*
import kotlinx.android.synthetic.main.toolbar.*

open class DebugActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "GoldenApp"
    private val className: String
        get() {
            val nomeDaClasse = javaClass.name
            return nomeDaClasse.substring(nomeDaClasse.lastIndexOf("."))
            // nomeDaClasse = br.com.goldenapp.DebugActivity
        }

    var generic_layout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, className + ".onCreate() foi chamado")
    }

    protected fun configuraMenuLateral() {
        var toogle = ActionBarDrawerToggle(
            this,
            generic_layout,
            toolbar,
            R.string.nav_abrir,
            R.string.nav_fechar
        )

        generic_layout?.addDrawerListener(toogle)
        toogle.syncState()

        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_produtos -> {
                val intent = Intent(this, TelaInicialActivity::class.java)
                Toast.makeText(this, "Menu de produtos", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
            R.id.nav_forum -> {
                val intent = Intent(this, ForumActivity::class.java)
                Toast.makeText(this, "Menu Fórum", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            R.id.nav_configuracao -> {
                val intent = Intent(this, ConfiguracaoActivity::class.java)
                Toast.makeText(this, "Menu Configurações", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            R.id.nav_localizacao -> {
                val intent = Intent(this, LocalizacaoActivity::class.java)
                Toast.makeText(this, "Menu Localização", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            R.id.nav_mensagem -> {
                val intent  = Intent(this, MensagemActivity::class.java)
                Toast.makeText(this, "Menu Mensagem", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }

        // fecha menu depois de tratar o evento
        generic_layout?.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, className + ".onStart() chamado")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, className + ".onResume() foi chamado")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, className + ".onStop() foi chamado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,className + ".onRestart() foi chamado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, className + ".onDestroy() foi chamado")
    }
}