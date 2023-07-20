package com.ezatpanah.koindi_retrofit_youtube.response

data class PhotoResponse(
    val hits: List<Hit>,
    val total: Int, // 34855
    val totalHits: Int // 500
) {
    data class Hit(
        val collections: Int, // 2258
        val comments: Int, // 282
        val downloads: Int, // 467113
        val id: Int, // 2295434
        val imageHeight: Int, // 3575
        val imageSize: Int, // 2938651
        val imageWidth: Int, // 5363
        val largeImageURL: String, // https://pixabay.com/get/gb7904c2fbafe3ea6b50f09d45743329899cfaa7b12f7b41396c01a731aa379a1ab31cce6b6809c87daf5aacb5890bf02fffea021b9865b19975d66234b613de0_1280.jpg
        val likes: Int, // 2163
        val pageURL: String, // https://pixabay.com/photos/spring-bird-bird-tit-spring-blue-2295434/
        val previewHeight: Int, // 99
        val previewURL: String, // https://cdn.pixabay.com/photo/2017/05/08/13/15/spring-bird-2295434_150.jpg
        val previewWidth: Int, // 150
        val tags: String, // spring bird, bird, tit
        val type: String, // photo
        val user: String, // JillWellington
        val userImageURL: String, // https://cdn.pixabay.com/user/2018/06/27/01-23-02-27_250x250.jpg
        val user_id: Int, // 334088
        val views: Int, // 786795
        val webformatHeight: Int, // 426
        val webformatURL: String, // https://pixabay.com/get/g501fc098896eef9e1147d7a69466c41526ece58d3d0a377a78e40ca3f2b03a7cd5de21efc3ee2355ea4a832179b31196_640.jpg
        val webformatWidth: Int // 640
    )
}