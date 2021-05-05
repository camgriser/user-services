package org.acg.user.service;

import org.acg.user.repository.entity.UserInfo;

interface UserService {

    fun save(userInfo: UserInfo)
    fun findByEmail(email: String): UserInfo?
    fun createPasswordResetTokenForUser(userInfo: UserInfo, token: String)
    fun changeUserPassword(userInfo: UserInfo, password: String)

}
