package org.acg.user_services.service;

import org.acg.user_services.repository.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(UserInfo userInfo, String token) {
        mailSender.send(constructResetTokenEmail("APP URL", token, userInfo));
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, String token, UserInfo userInfo) {
        String url = contextPath + "/user/changePassword?id=" +
                userInfo.getId() + "&token=" + token;
        String message = "Reset password message TODO:";
        return constructEmail("Reset Password", message + " \r\n" + url, userInfo);
    }

    /**
     * TODO: Update body of email.
     *
     * @param subject
     * @param body
     * @param user
     * @return
     */
    private SimpleMailMessage constructEmail(String subject, String body,
                                             UserInfo user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom("SUPPORT.EMAIL");
        return email;
    }
}
