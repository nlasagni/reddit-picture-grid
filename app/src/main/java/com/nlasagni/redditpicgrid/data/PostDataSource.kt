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

package com.nlasagni.redditpicgrid.data

import android.util.Log
import com.nlasagni.redditpicgrid.api.RedditService
import com.nlasagni.redditpicgrid.data.local.Post
import com.nlasagni.redditpicgrid.data.remote.ImageCheckStrategy
import com.nlasagni.redditpicgrid.data.remote.RetrievePostImageUrlStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Nicola Lasagni on 18/08/2021.
 */
class PostDataSource @Inject constructor(
    private val service: RedditService,
    private val mapper: PostListMapper,
    private val imageCheckStrategy: ImageCheckStrategy,
    private val retrievePostImageUrlStrategy: RetrievePostImageUrlStrategy
) {

    suspend fun searchPostsWithPictures(keyword: String): List<Post> {
        return withContext(Dispatchers.IO) {
            var posts: List<Post> = emptyList()
            try {
                val listingRoot = service.searchSubredditPosts(keyword)
                val remotePosts = mapper.mapToListOfPosts(listingRoot)
                posts = remotePosts
                    .filter { imageCheckStrategy.isImage(it) }
                    .map {
                        Post(
                            id = it.id,
                            title = it.title ?: "",
                            author = it.author ?: "",
                            ups = it.ups ?: 0,
                            down = it.down ?: 0,
                            thumbnailUrl = retrievePostImageUrlStrategy.retrieveThumbnailUrl(it),
                            imageUrl = retrievePostImageUrlStrategy.retrieveImageUrl(it)
                        )
                    }
            } catch (ex: HttpException) {
                Log.e(this::class.simpleName, "Error occurred while searching posts", ex)
            }
            posts
        }
    }

}