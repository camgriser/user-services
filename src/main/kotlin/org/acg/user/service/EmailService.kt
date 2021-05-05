package org.acg.user.service;

import org.acg.user.repository.entity.UserInfo

interface EmailService {

    fun sendEmail(userInfo: UserInfo, token: String)
}
