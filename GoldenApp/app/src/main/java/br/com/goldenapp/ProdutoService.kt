package br.com.goldenapp

object ProdutoService {

    fun getProdutos() : List<Produto> {

        val produtos = mutableListOf<Produto>()

        for (i in 1..10) {
            val p = Produto()
            p.nome = "Produto $i"
            p.descricao = "Descrição $i"
            p.proprietario = "Proprietário $i"
            p.foto = "https://static.mercadoshops.com/golden-bear-company_iZ993415424XsZ260557303XpZ5XfZ260557303-36447087997-5XvZgrandexIM.jpg?v=master-20200923_174657"
            produtos.add(p)
        }
        return produtos
    }
}