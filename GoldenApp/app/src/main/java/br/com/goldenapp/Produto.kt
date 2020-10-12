package br.com.goldenapp

import java.io.Serializable

class Produto: Serializable {

    var id: Long = 0
    var nome = ""
    var descricao = ""
    var proprietario = ""
    var foto = ""

    override fun toString(): String {
        return "Produto(nome=$nome)"
    }
}