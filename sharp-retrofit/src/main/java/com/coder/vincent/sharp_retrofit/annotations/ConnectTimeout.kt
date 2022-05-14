package com.coder.vincent.sharp_retrofit.annotations
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class ConnectTimeout(val timeout: Int)
