package org.sopt.first.data.response

data class ResponseJoinData(
    val success: Boolean,
    val message: String,
    val data: JoinData?
)

data class JoinData(
    val nickname: String,
)