package br.com.goldenapp

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import okhttp3.Response
import java.net.URL

object ProdutoService {

    val host = "https://guilhermemoreira99.pythonanywhere.com"
    val TAG = "WS_GoldenApp"

    fun getProdutos(context: Context) : List<Produto> {
        var produtos = ArrayList<Produto>()
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos"
            val json = HttpHelper.get(url)
            produtos = parserJson(json)
            //salvar offline
            for (p in produtos) {
                saveOffline(p)
            }
            return produtos
        }
        else {
            val dao = DatabaseManager.getProdutoDAO()
            val produtos = dao.findAll()
            return produtos
        }
    }

    fun getProduto (context: Context, id: Long): Produto? {

        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos/${id}"
            val json = HttpHelper.get(url)
            val produto = parserJson<Produto>(json)

            return produto
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produto = dao.getById(id)
            return produto
        }
    }

    fun save(produto: Produto): br.com.goldenapp.Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val json = HttpHelper.post("$host/produtos", produto.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(produto)
            return Response("OK", "Produto salvo no dispositivo")
        }
    }

    fun saveOffline(produto: Produto) : Boolean {
        val dao = DatabaseManager.getProdutoDAO()

        if (! existeProduto(produto)) {
            dao.insert(produto)
        }
        return true
    }

    fun existeProduto(produto: Produto): Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        return dao.getById(produto.id) != null
    }

    fun delete(produto: Produto): br.com.goldenapp.Response {
        if (AndroidUtils.isInternetDisponivel()) {
            val url = "$host/produtos/${produto.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            dao.delete(produto)
            return br.com.goldenapp.Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}