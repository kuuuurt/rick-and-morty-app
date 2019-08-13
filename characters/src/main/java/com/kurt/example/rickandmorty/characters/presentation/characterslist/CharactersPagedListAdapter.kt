package com.kurt.example.rickandmorty.characters.presentation.characterslist

import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kurt.example.rickandmorty.characters.R
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.presentation.BasePagedListAdapter
import com.kurt.example.rickandmorty.core.presentation.utils.createDefaultNavOptions

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class CharactersPagedListAdapter : BasePagedListAdapter<Character>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override val itemLayout = R.layout.list_item_character

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)

        if (character != null) {
            holder.itemView.apply {
                val imgCharacter by lazy { findViewById<ImageView>(R.id.img_character) }
                val txtName by lazy { findViewById<TextView>(R.id.txt_name) }

                Glide.with(context)
                    .load(character.image)
                    .into(imgCharacter)

                txtName.text = character.name

                setOnClickListener {
                    val extras = FragmentNavigatorExtras(
                        imgCharacter to "imgCharacter"
                    )
                    val direction = CharactersListFragmentDirections.actionViewCharacterDetails(character.id, character.image)
                    findNavController().navigate(direction, extras)
                }
            }
        }
    }
}