package org.acg.user.service;

import org.acg.user.repository.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
class EmailServiceImpl : EmailService {

    @Autowired
    private lateinit var mailSender: JavaMailSender

    override fun sendEmail(userInfo: UserInfo, token: String) {
        mailSender.send(constructResetTokenEmail("APP URL", token, userInfo))
    }

    private fun constructResetTokenEmail(contextPath: String, token: String, userInfo: UserInfo): SimpleMailMessage {
        val url = contextPath + "/user/changePassword?id=" +
                userInfo.id + "&token=" + token
        val message = "Reset password message TODO:"
        return constructEmail("Reset Password", "$message \r\n$url", userInfo)
    }

    /**
     * TODO: Update body of email.
     *
     * @param subject
     * @param body
     * @param user
     * @return
     */
    private fun constructEmail(subject: String, body: String, user: UserInfo): SimpleMailMessage {
        val email = SimpleMailMessage()
        email.subject = subject
        email.text = body
        email.setTo(user.email)
        email.from = "SUPPORT.EMAIL"
        return email;
    }
}
