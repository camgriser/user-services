package org.acg.user_services.service;

import org.acg.user_services.repository.entity.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    void save(UserInfo userInfo);
    UserInfo findByEmail(String email);
    void createPasswordResetTokenForUser(UserInfo userInfo, String token);
    void changeUserPassword(UserInfo userInfo, String password);

}
