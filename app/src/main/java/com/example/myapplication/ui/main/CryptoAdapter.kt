package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.CurrencyInfo


class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>(){

    private var mCryptoList = emptyList<CurrencyInfo>()
    private var mOnCurrencyClickListener : CurrencyListFragment.OnCurrencyClickListener? = null
    private var mRecyclerView : RecyclerView?= null


    class CryptoViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        var mName : TextView
        var mSymbol : TextView
        var mId : TextView

        init{
            mName = itemView.findViewById(R.id.tv_name)
            mSymbol = itemView.findViewById(R.id.tv_symbol)
            mId = itemView.findViewById(R.id.tv_id)
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.mRecyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto,parent,false)
        view.setOnClickListener{
           val position =  mRecyclerView?.getChildAdapterPosition(view) ?: -1
            if( position > -1 )
            mOnCurrencyClickListener?.onCurrencyClicked(position,mCryptoList.get(position))
        }
        return CryptoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val item = mCryptoList.get(position)
        holder.mName.text = item.name
        holder.mSymbol.text = item.symbol
        holder.mId.text = item.id.subSequence(0,1)

    }

    override fun getItemCount(): Int {
        return  mCryptoList.size
    }



    fun setCryptoList( list:List<CurrencyInfo>){
        this.mCryptoList = list
        this.notifyDataSetChanged()
    }

    fun setOnClickListener(listener: CurrencyListFragment.OnCurrencyClickListener?) {
        mOnCurrencyClickListener = listener
    }




}

