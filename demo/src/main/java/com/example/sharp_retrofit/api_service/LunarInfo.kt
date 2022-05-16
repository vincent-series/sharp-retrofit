package com.example.sharp_retrofit.api_service

data class LunarInfo(
    val code: Int,
    val `data`: Data,
    val log_id: Long,
    val msg: String,
    val time: Int
)

data class Data(
    val animal: String,
    val color_day: String,
    val color_hour: String,
    val color_month: String,
    val color_year: String,
    val constellation: String,
    val ganzhi_day: String,
    val ganzhi_hour: String,
    val ganzhi_month: String,
    val ganzhi_year: String,
    val gregorian_day: String,
    val gregorian_hour: String,
    val gregorian_month: String,
    val gregorian_year: String,
    val is_leap: Boolean,
    val is_same_year: Boolean,
    val is_today: Boolean,
    val lunar_day: String,
    val lunar_day_chinese: String,
    val lunar_hour: String,
    val lunar_hour_chinese: String,
    val lunar_month: String,
    val lunar_month_chinese: String,
    val lunar_year: String,
    val lunar_year_chinese: String,
    val term: Any,
    val week_name: String,
    val week_no: Int,
    val wuxing_day: String,
    val wuxing_hour: String,
    val wuxing_month: String,
    val wuxing_year: String
)