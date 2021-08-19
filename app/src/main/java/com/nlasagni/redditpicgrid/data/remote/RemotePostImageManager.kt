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

/**
 * Created by Nicola Lasagni on 19/08/2021.
 */
class RemotePostImageManager : ImageCheckStrategy, RetrievePostImageUrlStrategy {

    private val imageTypeSuffixSet = setOf(".jpg", ".jpeg", ".png")

    override fun isImage(post: Post): Boolean {
        return post.hint == Post.POST_HINT_IMAGE ||
                post.hint == Post.POST_HINT_LINK ||
                post.getFirstImage()?.url?.isNotEmpty() == true ||
                endsWithImageSuffix(post.url)
    }

    override fun retrieveThumbnailUrl(post: Post): String? {
        return post.thumbnail
    }

    override fun retrieveImageUrl(post: Post): String {
        // Here we assume that a post has at least one image url
        val url = post.url ?: ""
        if (url.isNotEmpty()) {
            return url
        }
        return post.getFirstImage()!!.url!!
    }

    private fun endsWithImageSuffix(string: String?): Boolean {
        return imageTypeSuffixSet.any { string?.endsWith(it) ?: false }
    }
}