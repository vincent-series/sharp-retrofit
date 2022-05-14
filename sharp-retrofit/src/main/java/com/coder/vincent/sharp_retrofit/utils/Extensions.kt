package com.coder.vincent.sharp_retrofit.utils

import okhttp3.HttpUrl

fun HttpUrl.jointEncodedPath() = encodedPath().replaceFirst("/", "")