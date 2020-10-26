package br.com.goldenapp

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: GoldenAppDatabase

    init {
        val contexto = GoldenAppApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            contexto,
            GoldenAppDatabase::class.java,
            "golden.sqllite"
        ).build()
    }

    fun getProdutoDAO(): ProdutoDAO {
        return dbInstance.produtoDAO()
    }
}