package com.rnm.rnmandroid

import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rnm.rnmandroid.di.IoDispatcher
import com.rnm.rnmandroid.features.characters.model.Character
import com.rnm.rnmandroid.repositories.MainRepository
import com.rnm.rnmandroid.services.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : ViewModel() {


    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState.asStateFlow()

    fun getCharacters(page: Int? = null) {
        _mainState.update { it.copy(isLoading = true, isError = false) }
        viewModelScope.launch(dispatcher) {
            when (val response = repository.getCharacters(page)) {
                is NetworkResponse.Success -> _mainState.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        nextPage = response.data.nextPage,
                        characters = it.characters + response.data.characters,
                    )
                }
                is NetworkResponse.Error -> _mainState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }
    }

    fun goBack() {
        _mainState.update { it.copy(character = null) }
    }

    fun retry() {
        getCharacters(mainState.value.nextPage?.split("=")?.last()?.toInt())
    }

    fun goToDetails(cardView: View, character: Character) {
        _mainState.update { it.copy(character = character, sharedView = cardView) }
    }
}