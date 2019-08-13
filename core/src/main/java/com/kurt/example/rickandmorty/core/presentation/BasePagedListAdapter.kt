package com.kurt.example.rickandmorty.core.presentation

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter

/**
 * Copyright 2018, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 12/09/2018
 */

abstract class BasePagedListAdapter<T>(
    itemsSame: (T, T) -> Boolean,
    contentsSame: (T, T) -> Boolean = { old, new -> old == new }
) : PagedListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = itemsSame(oldItem, newItem)
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = contentsSame(oldItem, newItem)
}) {
    abstract val itemLayout: Int

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_ITEM -> {
                bindItemViewHolder(holder, position)
            }
            else -> {
                throw(IllegalArgumentException("Unsupported View Type"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_ITEM -> {
                createItemViewHolder(parent)
            }
            else -> {
                throw(IllegalArgumentException("Unsupported View Type"))
            }
        }

    override fun getItemViewType(position: Int) = when {
        isPositionItem(position) -> TYPE_ITEM
        else -> throw IllegalArgumentException("Unsupported View Type")
    }

    open fun createItemViewHolder(parent: ViewGroup): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(itemLayout, parent, false)

        return object : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {}
    }

    abstract fun bindItemViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int)
    open fun isPositionItem(position: Int): Boolean = true


    companion object {
        const val TYPE_ITEM = 0
    }
}