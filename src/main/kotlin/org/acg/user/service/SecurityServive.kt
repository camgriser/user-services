package org.acg.user.service;

interface SecurityServive {

    fun findLoggedInUsername(): String
    fun autologin(username: String, token: String)
    fun validatePasswordResetToken(id: Long, token: String): String

}
