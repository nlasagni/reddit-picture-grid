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

package com.nlasagni.redditpicgrid.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents a subreddit from Reddit APIs.
 *
 * Created by Nicola Lasagni on 18/08/2021.
 */
data class Post (
    @field:SerializedName("id") val id: String,
    @field:SerializedName("post_hint") val hint: String,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("author") val author: String?,
    @field:SerializedName("ups") val ups: Long?,
    @field:SerializedName("downs") val down: Long?,
    @field:SerializedName("thumbnail") val thumbnail: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("preview") val preview: Preview?
) {
    companion object {
        const val POST_HINT_IMAGE = "image"
        const val POST_HINT_LINK = "link"
    }

    fun getFirstImage(): ImageSource? {
        return preview?.images?.first()?.source
    }
}