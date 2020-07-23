package org.acg.user_services.service;

import org.acg.user_services.repository.entity.UserInfo;

public interface EmailService {

    void sendEmail(UserInfo userInfo, String token);
}
