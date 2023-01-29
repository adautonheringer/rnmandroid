package com.rnm.rnmandroid

import android.view.View
import com.rnm.rnmandroid.features.characters.model.Character

data class MainState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val nextPage: String? = null,
    val characters: List<Character> = listOf(),
    val sharedView: View? = null,
    val character: Character? = null,
)