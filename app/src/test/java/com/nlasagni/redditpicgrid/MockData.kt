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

package com.nlasagni.redditpicgrid

import com.nlasagni.redditpicgrid.data.remote.*

/**
 * Created by Nicola Lasagni on 18/08/2021.
 */
object MockData {

    val postId = "p62b72"
    val postTitle = "Where is our food?"
    val postAuthor = "mattia_cecchi"
    val postUps = 917.toLong()
    val postDowns = 0.toLong()
    val postThumbnail = "https://b.thumbs.redditmedia.com/" +
            "3u_0JieNR-tkIZ0ngCDy3cEmC-eKTbKAT9xT2UuE_Wg.jpg"
    val postImageSourceUrl = "https://preview.redd.it/" +
            "8wpoabiyqwh71.jpg?auto=webp&amp;s=abd29248c3129bbd64fdddfac136b9c8b4610c1c"
    val post = Post(
        id = postId,
        title = postTitle,
        author = postAuthor,
        ups = postUps,
        down = postDowns,
        thumbnail = postThumbnail,
        preview = Preview(
            listOf(
                Image(
                    source = ImageSource(
                        url = postImageSourceUrl
                    )
                )
            )
        ),
    )
    val listing = ListingRoot(
        content = ListingContent(
            children = listOf(
                ListingContentChild(
                    post = post
                )
            )
        )
    )

}