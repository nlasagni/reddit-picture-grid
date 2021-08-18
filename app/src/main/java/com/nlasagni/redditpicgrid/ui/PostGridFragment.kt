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

package com.nlasagni.redditpicgrid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.nlasagni.redditpicgrid.R
import com.nlasagni.redditpicgrid.viewmodel.PostGridViewModel
import com.nlasagni.redditpicgrid.viewmodel.model.PostGrid
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Nicola Lasagni on 18/08/2021.
 */
class PostGridFragment : Fragment() {

    private val viewModel: PostGridViewModel by viewModels()
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.post_grid, container, false)
        subscribeForUpdates()
        return view
    }

    private fun subscribeForUpdates() {
        viewModel.postGridLiveData.observe(viewLifecycleOwner) {
            renderViewModel(it)
        }
    }

    private fun renderViewModel(postGrid: PostGrid) {
        TODO("Not implemented")
    }

    private fun search(keyword: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.search(keyword)
        }
    }

}