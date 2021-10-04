package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.data.CurrencyInfo
import com.example.myapplication.ui.main.CurrencyListFragment

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container,CurrencyListFragment.newInstance() )
                .commitNow()


        }
        val fragment = supportFragmentManager.fragments[0] as CurrencyListFragment
        fragment.onCurrencyClickListener =  object : CurrencyListFragment.OnCurrencyClickListener {
            override fun onCurrencyClicked(pos: Int, currency: CurrencyInfo) {
                Toast.makeText(this@DemoActivity,"position = ${pos} , currency = ${currency.name}",Toast.LENGTH_SHORT).show()
            }

        }

    }


}