package org.acg.user_services.service;

import org.acg.user_services.repository.UserRepo;
import org.acg.user_services.repository.entity.PasswordResetToken;
import org.acg.user_services.repository.entity.Role;
import org.acg.user_services.repository.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = userRepo.findByEmail(email);

        if (userInfo == null) {
            throw new UsernameNotFoundException("The user was not found!");
        }

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for (Role role : userInfo.getRoles()) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(userInfo.getEmail(), userInfo.getToken(), grantedAuthoritySet);
    }

}
