package org.acg.user.domain;

data class PasswordDto (
    val token: String,
    val confirmToken: String
)