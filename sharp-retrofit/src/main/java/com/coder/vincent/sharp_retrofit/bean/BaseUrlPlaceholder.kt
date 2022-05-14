package com.coder.vincent.sharp_retrofit.bean

import okhttp3.HttpUrl

class BaseUrlPlaceholder private constructor() {
    companion object {
        val instance: HttpUrl = HttpUrl.get("https://sharp.retrofit.com/")
    }
}