package com.coder.vincent.sharp_retrofit.bean

import com.coder.vincent.sharp_retrofit.annotations.CallTimeout
import com.coder.vincent.sharp_retrofit.annotations.ConnectTimeout
import com.coder.vincent.sharp_retrofit.annotations.ReadTimeout
import com.coder.vincent.sharp_retrofit.annotations.WriteTimeout

data class TimeoutSetting(
    val call: CallTimeout? = null,
    val connect: ConnectTimeout? = null,
    val read: ReadTimeout? = null,
    val write: WriteTimeout? = null,
)

