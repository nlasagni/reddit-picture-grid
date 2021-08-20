![Android CI status](https://github.com/nlasagni/reddit-picture-grid/actions/workflows/android.yml/badge.svg)

# RedditPicGrid Android app

A technical assignment that involves making an Android app that shows pictures on a grid, coming from Reddit APIs.

## 1.1 Description

The goal is to implement an application for a photo gallery populated with the data obtained from https://www.reddit.com/r/{KEYWORD}/top.json (where {KEYWORD} is a placeholder of the searched word ).

The photo gallery can be a very simple grid layout. When the user taps a photo, he should be able to see the photo in full screen, with some details present in the JSON, he must also have the ability to zoom in and scroll to the next photo.

Add a search field at the top. When the user types something, they see the new content as they type. If there is no content available with the keyword provided by the user, let them know.

## 1.2 Guidelines

1. Place your project in a Bitbucket or GitHub repository.
2. Make meaningful and concise commits. Commit frequently. With a good message explaining the code changes.
3. The minSdk must be 21.
4. Moderate use of external libraries will be particularly appreciated.
5. The code must be written in Kotlin.
6. Follow Google's guidelines and use proper patterns.

## 1.3 Optional bonus tasks

1. Make the app hassle-free even offline by working with cached data. You can use any solution to achieve the result, but motivate your choice.
2. Make it possible to save favorite photos in a dedicated section.
3. A testable app shows how much you care about quality.
4. There must be some UI transactions when selecting the photo.
5. Freestyle. We love developers with initiatives. If you have any idea how to improve the free project do it.

## 1.4 Solution

The app has been designed following the 
[official guide to app architecture](https://developer.android.com/jetpack/guide).
In particular the main classes involved in the solution are:

- **PostDataSource:** responsible of retrieving posts that match the keyword searched.
- **PostViewModel:** responsible of manipulating data coming from the datasource and make them ui-ready.
- **PostGridFragment, PostDetailFragment:** Fragments that represent the UI of the app. 

In order to achieve the main goals of the assignment, the following dependencies has been used:

- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection.
- [Gson](https://github.com/google/gson), [Retrofit2](https://square.github.io/retrofit/) for making HTTP calls and convert JSON to objects.
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) for navigating between Fragments.
- [Picasso](https://square.github.io/picasso/) for loading images.
- [TouchImageView](https://github.com/MikeOrtiz/TouchImageView) for making image zoomable.
