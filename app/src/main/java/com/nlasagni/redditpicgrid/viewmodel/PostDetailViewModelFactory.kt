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

package com.nlasagni.redditpicgrid.viewmodel

import com.nlasagni.redditpicgrid.data.local.Post
import com.nlasagni.redditpicgrid.viewmodel.model.PostDetail
import javax.inject.Inject

/**
 * Utility class that converts a list of [Post] to a list of [PostDetail].
 *
 * Created by Nicola Lasagni on 19/08/2021.
 */
class PostDetailViewModelFactory @Inject constructor() {

    fun createModel(posts: List<Post>): List<PostDetail> {
        return posts.map {
            PostDetail(
                id = it.id,
                title = it.title,
                author = it.author,
                ups = it.ups.toString(),
                down = it.down.toString(),
                imageUrl = it.imageUrl
            )
        }
    }

}