package com.rickandmorty.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.databinding.ActivityCharacterListBinding
import com.rickandmorty.presentation.information.InformationActivity
import org.koin.android.ext.android.inject

class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by inject()

    lateinit var binding: ActivityCharacterListBinding
    lateinit var adapter: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupList()
        viewModel.loadPokeList()
    }

    private fun setupViewModel() {
        viewModel.loadStateLiveData.observe(this) { state ->
            when (state) {
                is CharacterListState.Loading -> {}
                is CharacterListState.Success -> showResponse(state.result)
                is CharacterListState.Error -> {}
            }
        }
    }

    private fun showResponse(result: List<CharacterApiResultResponse>) {
        // hideLoading()
        //hideLoadingMore()
        adapter.addCharacterList(result)
    }

    private fun setupList() {
        adapter = CharacterListAdapter { character ->
            val intent = Intent(this, InformationActivity::class.java)
            startActivity(intent)
        }
        binding.recyclerviewCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@CharacterListActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = this@CharacterListActivity.adapter

//            addOnScrollListener(object : RecyclerView.OnScrollListener() {

//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    if (!recyclerView.canScrollVertically(1) && !this@CharacterListActivity.adapter.isFiltering()) {
//                        viewModel.loadMore()
//                    }
//
//                    super.onScrolled(recyclerView, dx, dy)
//                }
//            })
        }
    }
}