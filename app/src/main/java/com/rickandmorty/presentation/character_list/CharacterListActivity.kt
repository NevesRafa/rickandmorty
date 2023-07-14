package com.rickandmorty.presentation.character_list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rickandmorty.R
import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.databinding.ActivityCharacterListBinding
import com.rickandmorty.internal.extension.gone
import com.rickandmorty.internal.extension.setErrorStyle
import com.rickandmorty.internal.extension.visible
import com.rickandmorty.presentation.details.DetailsActivity
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
                is CharacterListState.Loading -> showLoading()
                is CharacterListState.Success -> showResponse(state.result)
                is CharacterListState.Error -> showError(state.errorMessage)
            }
        }
        viewModel.loadMoreStateLiveData.observe(this) { state ->
            when (state) {
                is CharacterListState.Loading -> showLoadingMore()
                is CharacterListState.Success -> showResponse(state.result)
                is CharacterListState.Error -> showError(state.errorMessage)
            }
        }
    }

    private fun showResponse(result: List<CharacterApiResultResponse>) {
        hideLoading()
        hideLoadingMore()
        adapter.addCharacterList(result)
    }

    private fun showError(errorMessage: String?) {
        hideLoading()
        hideLoadingMore()
        Snackbar.make(binding.root, getString(R.string.error_message, errorMessage), Snackbar.LENGTH_LONG)
            .setErrorStyle()
            .show()
    }

    private fun showLoading() {
        binding.recyclerviewCharacters.gone()
        binding.subtitle.gone()
        binding.loading.visible()
    }

    private fun showLoadingMore() {
        binding.recyclerviewCharacters.scrollToPosition(adapter.itemCount - 1)
        binding.loadingBottom.visible()
    }

    private fun hideLoading() {
        binding.loading.gone()
        binding.subtitle.visible()
        binding.recyclerviewCharacters.visible()
    }

    private fun hideLoadingMore() {
        binding.loadingBottom.gone()
    }

    private fun setupList() {
        adapter = CharacterListAdapter { character ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.EXTRA_CHARACTER_INFORMATION, character)
            startActivity(intent)
        }
        binding.recyclerviewCharacters.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@CharacterListActivity, 2, GridLayoutManager.VERTICAL, false)
            adapter = this@CharacterListActivity.adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.loadMore()
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            })
        }
    }
}