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

package com.nlasagni.redditpicgrid.ui.postdetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.nlasagni.redditpicgrid.R
import com.nlasagni.redditpicgrid.viewmodel.model.PostDetail
import com.ortiz.touchview.TouchImageView
import com.squareup.picasso.Picasso

/**
 * Created by Nicola Lasagni on 19/08/2021.
 */
class PostDetailPagerAdapter(
    private val context: Context,
    private val postDetails: List<PostDetail>
): PagerAdapter() {

    override fun getCount(): Int {
        return postDetails.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.post_detail_item, container, false)
        val postDetail = postDetails[position]
        val title: TextView = view.findViewById(R.id.postTitle)
        val author: TextView = view.findViewById(R.id.postAuthor)
        val ups: TextView = view.findViewById(R.id.postUps)
        val down: TextView = view.findViewById(R.id.postDown)
        val image: TouchImageView = view.findViewById(R.id.postImage)
        title.text = postDetail.title
        author.text = postDetail.author
        ups.text = postDetail.ups
        down.text = postDetail.down
        Picasso.get()
            .load(postDetail.imageUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(image)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}