package br.com.goldenapp

import androidx.room.Room

object DatabaseManager {

    // singleton
    private var dbInstance: GoldenAppDatabase

    init {
        val contexto = GoldenAppApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            contexto, // contexto global
            GoldenAppDatabase::class.java, // Referência da classe do banco
            "golden.sqllite" // nome do arquivo do banco
        ).build()
    }

    fun getProdutoDAO(): ProdutoDAO {
        return dbInstance.produtoDAO()
    }
}