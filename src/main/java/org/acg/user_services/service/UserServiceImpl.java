package org.acg.user_services.service;

import org.acg.user_services.repository.PasswordResetTokenRepo;
import org.acg.user_services.repository.RoleRepo;
import org.acg.user_services.repository.UserRepo;
import org.acg.user_services.repository.entity.PasswordResetToken;
import org.acg.user_services.repository.entity.Role;
import org.acg.user_services.repository.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordResetTokenRepo passwordTokenRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setToken(bCryptPasswordEncoder.encode(userInfo.getToken()));

        // DEFAULT ROLE is USER
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName("USER"));
        userInfo.setRoles(roles);

        userRepo.save(userInfo);
    }

    @Override
    public UserInfo findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(UserInfo user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUser(user);
        myToken.setToken(token);
        myToken.setExpiration();
        passwordTokenRepo.save(myToken);
    }

    @Override
    public void changeUserPassword(UserInfo userInfo, String password) {
        userInfo.setToken(bCryptPasswordEncoder.encode(password));
        userRepo.save(userInfo);
    }
}
