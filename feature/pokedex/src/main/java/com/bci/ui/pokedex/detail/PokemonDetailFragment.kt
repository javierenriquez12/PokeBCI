package com.bci.ui.pokedex.detail

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.bci.common.presentation.base.BaseDialogFragment
import com.bci.common.presentation.fitLayoutParams
import com.bci.common.presentation.isVisible
import com.bci.common.presentation.showMessage
import com.bci.data.domain.model.Pokemon
import com.bci.pokedex.R
import com.bci.pokedex.databinding.FragmentPokemonDetailBinding
import com.bci.resources.R.style.CustomChipChoice
import com.bci.ui.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.bci.resources.R.style.FullScreenDialogStyle
import com.bci.resources.R.color.lightYellow
import com.bci.resources.R.color.red
import com.bci.resources.R.color.purple_200
import com.bci.uicomponents.avatar.model.BCIAvatarType

@AndroidEntryPoint
class PokemonDetailFragment : BaseDialogFragment() {

    companion object {
        private const val argumentsIdPokemonKey = "idPokemon"
    }

    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var idPokemon: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            FullScreenDialogStyle
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setIdPokemon()
        setUpPokemonAvatar()
        setUpObserver()
        setUpBack()
        addLoader()
        viewModel.fetchPokemon(idPokemon)
    }

    private fun setUpBack() {
        binding.backImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setIdPokemon() {
        idPokemon = arguments?.getString(argumentsIdPokemonKey) ?: ""
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchDetailPokemon.collect { uiState ->
                    when (uiState) {
                        is PokemonDetailUiState.Success -> {
                            setDataDetailPokemon(uiState.pokemon)
                        }
                        is PokemonDetailUiState.Error -> {
                            requireContext().showMessage(uiState.exception.message.toString())
                        }
                        is PokemonDetailUiState.LoadingDetail -> {
                            showOrHideLoad(
                                binding.pokemonDetailContainerConstraintLayout,
                                uiState.isLoad
                            )
                        }

                        is PokemonDetailUiState.SuccessEncounter -> {
                            bindingLocationsPokemon(uiState.string)
                        }
                        is PokemonDetailUiState.SuccessEvolution -> {
                            bindingImagePokemonEvolution(uiState.idEvolution)
                        }
                    }
                }
            }
        }
    }

    private fun bindingLocationsPokemon(locations: List<String>) {
        if (locations.isNotEmpty())
            locations.forEach {
                binding.pokemonDetailLocationsPokemonChipGroup.addView(
                    addChip(it, purple_200)
                )
            }
        else {
            binding.pokemonDetailLocationsPokemonChipGroup.isVisible(false)
            binding.pokemonDetailLocationsTitlePokemonTextView.isVisible(false)
        }
    }

    private fun setDataDetailPokemon(pokemon: Pokemon) {
        binding.pokemonDetailNameTextView.text =
            pokemon.name.replaceFirstChar { it.uppercaseChar() }
        binding.pokemonDetailTypeTextView.text = requireContext().getString(
            R.string.type_pokemon,
            pokemon.types.first()
        )
        setAbilities(pokemon.abilities)
        setMovesPokemon(pokemon.moves)
    }

    private fun setMovesPokemon(moves: List<String>) {
        moves.forEach {
            binding.pokemonDetailMovesPokemonChipGroup.addView(
                addChip(it, red)
            )
        }
    }

    private fun addChip(textChip: String, @ColorRes backgroundColor: Int): View =
        Chip(requireContext(), null, CustomChipChoice).apply {
            this.layoutParams = this.fitLayoutParams()
            text = textChip
            chipBackgroundColor = ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    backgroundColor
                )
            )

        }

    private fun setAbilities(abilities: List<String>) {
        abilities.forEach {
            binding.pokemonDetailSkillsChipGroup.addView(
                addChip(it, lightYellow)
            )
        }
    }

    private fun bindingImagePokemonEvolution(idEvolution: String) {
        if (idEvolution.isNotEmpty()) {
            Glide.with(requireContext())
                .load(
                    Utils.getUrlImagePokemon((idEvolution))
                )
                .placeholder(android.R.color.transparent)
                .into(binding.evolutionPokemonImageView)
            binding.evolutionPokemonImageView.isVisible(true)
            binding.pokemonEvolutionTitleTextView.isVisible(true)
        } else {
            binding.evolutionPokemonImageView.isVisible(false)
            binding.pokemonEvolutionTitleTextView.isVisible(false)
        }
    }

    private fun setUpPokemonAvatar() {
        binding.pokemonDetailBCIAvatar.run {
            this.type = BCIAvatarType.IMG
            this.setImageWithUrl(Utils.getUrlImagePokemon(idPokemon))
        }
    }
}