package com.example.datalit.adapter

import androidx.annotation.MainThread
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<ModelT, ViewHolderT : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<ViewHolderT>() {

    companion object {
        const val PAYLOAD_NO_ANIMATION = -1
    }

    protected val items: MutableList<ModelT> = ArrayList()

    fun items(): List<ModelT> {
        return items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun size(): Int {
        return items.size
    }

    protected fun <T : ModelT> internalUpdateItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
    }


    @MainThread
    fun <T : ModelT> diffUtilUpdateItems(diffResult: DiffUtil.DiffResult) {
        diffResult.dispatchUpdatesTo(this)
    }


    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun isNotEmpty(): Boolean {
        return !isEmpty()
    }

    fun getItem(position: Int): ModelT? {
        return if (position < 0 || position >= items.size) null else items[position]
    }

    fun <T : ModelT> add(item: T, index: Int) {
        val initialSize = items.size
        items.add(index, item)

        //We do it that way to prevent some issues. Check links from section #1 in head of file.
        if (initialSize == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemInserted(index)
        }
    }

    fun <T : ModelT> add(item: T): Int {
        val position = items.size
        add(item, position)
        return position
    }

    fun <T : ModelT> addAll(items: List<T>, index: Int) {
        if (items.isEmpty()) {
            return
        }
        val initialSize = this.items.size
        this.items.addAll(index, items)

        //We do it that way to prevent some issues. Check links from section #1 in head of file.
        if (initialSize == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(index, items.size)
        }
    }

    fun <T : ModelT> addAll(items: List<T>) {
        addAll(items, this.items.size)
    }

    fun <T : ModelT> replace(item: T, position: Int, payload: Any?): ModelT? {
        var replacedPosition = position
        if (replacedPosition > items.size) {
            replacedPosition = items.size
        }
        val oldItem = items.removeAt(replacedPosition)
        items.add(replacedPosition, item)
        if (payload == null) {
            notifyItemChanged(replacedPosition)
        } else {
            notifyItemChanged(replacedPosition, payload)
        }
        return oldItem
    }

    fun <T : ModelT> replace(item: T, position: Int): ModelT? {
        return replace(item, position, PAYLOAD_NO_ANIMATION)
    }

    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun <T : ModelT> replaceAll(items: List<T>) {
        clear()
        addAll(items)
    }

    fun remove(position: Int): ModelT? {
        if (position < 0 || position > items.size) {
            return null
        }
        val removedItem = items.removeAt(position)

        //We do it that way to prevent some issues. Check links from section #1 in head of file.
        if (items.isEmpty() || position == 0) {
            notifyDataSetChanged()
        } else {
            notifyItemRemoved(position)
        }
        return removedItem
    }

    fun <T : ModelT> removeAll(items: List<T>) {
        this.items.removeAll(items)
        notifyDataSetChanged()
    }

}