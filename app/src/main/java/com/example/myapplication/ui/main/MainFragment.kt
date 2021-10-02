package com.example.myapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.myapplication.MyApplication
import com.example.myapplication.R

import com.example.myapplication.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainFragmentBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding =  MainFragmentBinding.inflate(inflater)

        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,MainViewModelFactory((requireActivity().application as MyApplication).repository)).get(MainViewModel::class.java)
        val adapter = CryptoAdapter()

        mainFragmentBinding.recyclerView.adapter =  adapter
        this.setHasOptionsMenu(true)
        viewModel.getCryptoList().observe(viewLifecycleOwner,  {
            adapter.setCryptoList(it)
        } )

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.load -> {viewModel.loadList();  true;}
            R.id.sort -> {viewModel.updateSort();  true;}
            else -> super.onOptionsItemSelected(item)
        }

    }

}