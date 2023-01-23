package com.bci.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bci.common.presentation.base.BaseFragment
import com.bci.common.presentation.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.bci.data.domain.model.Result
import com.bci.pokedex.databinding.FragmentPokemonBinding

@AndroidEntryPoint
class PokemonFragment : BaseFragment() {
    private lateinit var binding: FragmentPokemonBinding
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPokemons()
        setUpListPokemons()
        requestSearch()
    }

    private fun requestSearch() {
        binding.pokemonSearchView.requestFocus()
    }

    private fun setUpSearchPokemon() {
        val listFilter = adapter.listPokemon
        binding.pokemonSearchView.setOnQueryTextListener(
            object : OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    setUpRecyclerPokemons(
                        listFilter.filter {
                            it.name.startsWith(text ?: "")
                        }
                    )
                    return false
                }
            }
        )
    }

    private fun loadPokemons() {
        pokemonViewModel.fetchPokemons()
    }

    private fun setUpRecyclerPokemons(listPokemon: List<Result>) {
        this.adapter = PokemonAdapter(listPokemon, ::actionDetailPokemonFragment)
        binding.pokemonRecyclerView.apply {
            adapter = this@PokemonFragment.adapter
        }
    }

    private fun actionDetailPokemonFragment(idPokemon: String) {
        val action =
            PokemonFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(
                idPokemon
            )
        findNavController().navigate(action)
    }

    private fun setUpListPokemons() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonViewModel.fetchPokemons.collect { uiState ->
                    when (uiState) {
                        is PokemonUiState.Success -> {
                            setUpRecyclerPokemons(uiState.pokemons)
                            setUpSearchPokemon()
                        }
                        is PokemonUiState.Error -> {
                            requireContext().showMessage(uiState.exception.message.toString())
                        }
                        is PokemonUiState.Loading -> {
                            if(uiState.isLoad){
                                binding.pokemonProgressCircle.visibility = View.VISIBLE
                                binding.pokemonRecyclerView.visibility = View.GONE
                            } else {
                                binding.pokemonProgressCircle.visibility = View.GONE
                                binding.pokemonRecyclerView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }
    }
}
