package com.rnm.rnmandroid.features.characters

import android.view.View
import com.rnm.rnmandroid.features.characters.model.Character

interface CharactersAdapterListener {
    fun onCharacterClicked(cardView: View, character: Character)
    fun getNextCharacters(page: Int?)
}