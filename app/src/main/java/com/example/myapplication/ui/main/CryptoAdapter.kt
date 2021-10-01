package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Currency

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>(){

    private var cryptoList = emptyList<Currency>()

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
        val item = cryptoList.get(position)
        holder.name.text = item.name
        holder.symbol.text = item.symbol.subSequence(0,1)

    }

    override fun getItemCount(): Int {
        return  cryptoList.size
    }

    fun setCryptoList( list:List<Currency>){
        this.cryptoList = list
        this.notifyDataSetChanged()
    }


}

