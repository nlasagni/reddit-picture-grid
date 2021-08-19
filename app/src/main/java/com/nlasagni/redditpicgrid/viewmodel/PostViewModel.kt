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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nlasagni.redditpicgrid.data.PostDataSource
import com.nlasagni.redditpicgrid.data.local.Post
import com.nlasagni.redditpicgrid.viewmodel.model.PostDetail
import com.nlasagni.redditpicgrid.viewmodel.model.PostGrid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nicola Lasagni on 18/08/2021.
 */
@HiltViewModel
class PostViewModel @Inject constructor(
    private val dataSource: PostDataSource,
    private val postGridModelFactory: PostGridModelFactory,
    private val postDetailViewModelFactory: PostDetailViewModelFactory
) : ViewModel() {

    var postListCache = emptyList<Post>()
    val postGridLiveData = MutableLiveData<PostGrid>()
    val postDetailsLiveData = MutableLiveData<List<PostDetail>>()
    var selectedPostIndex = 0

    fun search(keyword: String) {
        viewModelScope.launch {
            postListCache = dataSource.searchPostsWithPictures(keyword)
            postGridLiveData.value = postGridModelFactory.createModel(postListCache)
        }
    }

    fun loadPostDetails() {
        postDetailsLiveData.value = postDetailViewModelFactory.createModel(postListCache)
    }

}