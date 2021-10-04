package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.data.Currency
import com.example.myapplication.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                .replace(R.id.container,MainFragment.newInstance() )
                .commitNow()


        }
        val fragment = supportFragmentManager.fragments[0] as MainFragment
        fragment.onCurrencyClickListener =  object : MainFragment.OnCurrencyClickListener {
            override fun onCurrencyClicked(pos: Int, currency: Currency) {
                Toast.makeText(this@MainActivity,"position = ${pos} , currency = ${currency.name}",Toast.LENGTH_SHORT).show()
            }

        }

    }


}