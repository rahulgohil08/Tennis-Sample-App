package com.theworld.androidtemplatewithnavcomponents.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.theworld.androidtemplatewithnavcomponents.data.stringer.response.Stringer
import com.theworld.androidtemplatewithnavcomponents.databinding.LayoutDataBinding

class StringerAdapter(private val listener: StringerInterface) :
    ListAdapter<Stringer, StringerAdapter.CustomerViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding =
            LayoutDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    inner class CustomerViewHolder(private val binding: LayoutDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.imgDelete.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDelete(getItem(position))
                }
            }

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    listener.onClick(getItem(position))

                }
            }
        }


        fun bind(item: Stringer) {

            binding.apply {
                tvName.text = item.name
                tvDescription.text = item.address
            }

        }


    }

    class DiffCallback : DiffUtil.ItemCallback<Stringer>() {
        override fun areItemsTheSame(old: Stringer, aNew: Stringer) =
            old.stringerID == aNew.stringerID

        override fun areContentsTheSame(old: Stringer, aNew: Stringer) =
            old == aNew
    }


    interface StringerInterface {
        fun onClick(stringer: Stringer)
        fun onDelete(stringer: Stringer)
    }

}