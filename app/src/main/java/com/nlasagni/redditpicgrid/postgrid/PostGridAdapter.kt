/*
 * MIT License
 *
 * Copyright (c) 2021 Nicola Lasagni
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nlasagni.redditpicgrid.postgrid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nlasagni.redditpicgrid.R
import com.nlasagni.redditpicgrid.postgrid.model.PostGridItem
import com.squareup.picasso.Picasso

/**
 * Created by Nicola Lasagni on 19/08/2021.
 */
class PostGridAdapter(
    private val itemClickListener: OnItemClickListener
) : ListAdapter<PostGridItem, PostGridAdapter.ViewHolder>(PostGridItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position, itemClickListener)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private var title: TextView = itemView.findViewById(R.id.postTitle)
        private val image: ImageView = itemView.findViewById(R.id.postImage)

        fun bind(postGridItem: PostGridItem, position: Int, itemClickListener: OnItemClickListener) {
            title.text = postGridItem.title
            Picasso.get()
                .load(postGridItem.imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(image)
            view.setOnClickListener {
                itemClickListener.onItemClick(postGridItem, position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(postGridItem: PostGridItem, position: Int)
    }

}

class PostGridItemDiffCallback : DiffUtil.ItemCallback<PostGridItem>() {
    override fun areItemsTheSame(oldItem: PostGridItem, newItem: PostGridItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostGridItem, newItem: PostGridItem): Boolean {
        return oldItem == newItem
    }
}