package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Crypto

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>(){

    private var cryptoList = emptyList<Crypto>()

    class CryptoViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        var name : TextView
        var symbol : TextView

        init{
            name = itemView.findViewById(R.id.tv_name)
            symbol = itemView.findViewById(R.id.tv_symbol)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto,parent,false)
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.name.text = "BITCOIN"
        holder.symbol.text = "B"

    }

    override fun getItemCount(): Int {
        return  cryptoList.size
    }

    fun setCryptoList( list:List<Crypto>){
        this.cryptoList = list
        this.notifyDataSetChanged()
    }


}

