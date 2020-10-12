package br.com.goldenapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProdutosAdapter (

    val produtos: List<Produto>,
    val onClick: (Produto) -> Unit
):RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder>() {

    class ProdutosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val cardNome: TextView
        val cardImg: ImageView
        val cardProgress: ProgressBar

        init {
            cardNome = view.findViewById(R.id.cardNome)
            cardImg = view.findViewById(R.id.cardImg)
            cardProgress = view.findViewById(R.id.cardProgressBar)
        }
    }

    override fun getItemCount() = this.produtos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_produto,
            parent, false)

        val holder = ProdutosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ProdutosViewHolder, posicao: Int) {

        val produto = this.produtos[posicao]

        holder.cardNome.text = produto.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(holder.itemView.context).load(produto.foto).fit().into(holder.cardImg,
        object : com.squareup.picasso.Callback {

            override fun onSuccess() {
                holder.cardProgress.visibility = View.GONE
            }

            override fun onError() {
                holder.cardProgress.visibility = View.GONE
            }
        })
        holder.itemView.setOnClickListener { onClick(produto)}
    }
}