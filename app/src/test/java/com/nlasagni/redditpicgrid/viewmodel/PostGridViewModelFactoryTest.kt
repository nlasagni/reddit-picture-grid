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

import com.nlasagni.redditpicgrid.MockData
import com.nlasagni.redditpicgrid.data.PostListMapper
import com.nlasagni.redditpicgrid.viewmodel.model.PostGrid
import com.nlasagni.redditpicgrid.viewmodel.model.PostGridItem
import org.junit.Assert
import org.junit.Test

/**
 * Created by Nicola Lasagni on 18/08/2021.
 */
class PostGridViewModelFactoryTest {

    private val mapper = PostListMapper()

    @Test
    fun `should create a model for UI from a listing correctly`()  {
        val viewModelFactory = PostGridModelFactory(mapper)
        val postGridItem = PostGridItem(
            MockData.postId,
            MockData.postTitle,
            MockData.postThumbnail
        )
        val expected = PostGrid(listOf(postGridItem))
        val uiModel = viewModelFactory.createModel(MockData.listing)
        Assert.assertEquals(expected, uiModel)
    }

}