package com.aksh.crickwatch.data.model

data class Match(
    val id: String,
    val dateTimeGMT: String,
    val matchType: String,
    val status: String,
    val ms: String,
    val t1: String,
    val t2: String,
    val t1s: String?,
    val t2s: String?,
    val t1img: String,
    val t2img: String,
    val series: String
)