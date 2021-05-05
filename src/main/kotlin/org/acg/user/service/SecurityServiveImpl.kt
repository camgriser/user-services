package org.acg.user.service;

import org.acg.user.repository.PasswordResetTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
class SecurityServiveImpl : SecurityServive {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private lateinit var userDetailsService: UserDetailsService
    @Autowired
    private lateinit var passwordTokenRepo: PasswordResetTokenRepo

    override fun findLoggedInUsername(): String {
        val userDetails = SecurityContextHolder.getContext().authentication.details

        if (userDetails is UserDetails) {
            return userDetails.username
        }

        return ""
    }

    override fun autologin(username: String, token: String) {
        val userDetails = userDetailsService.loadUserByUsername(username)
        val usernamePasswordAuthenticationToken =
                UsernamePasswordAuthenticationToken(userDetails, token, userDetails.authorities)

        authenticationManager.authenticate(usernamePasswordAuthenticationToken)

        if (usernamePasswordAuthenticationToken.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
        }
    }

    override fun validatePasswordResetToken(id: Long, token: String): String {
        val passToken =
                passwordTokenRepo.findByToken(token)
        if ((passToken == null) || (passToken.user.id != id)) {
            return "invalidToken"
        }

        val cal = Calendar.getInstance()
        if ((passToken.expireDate.time - cal.time.time) <= 0) {
            return "expired";
        }

        val user = passToken.user
        val auth = UsernamePasswordAuthenticationToken(user, null
            , listOf(SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")))
        SecurityContextHolder.getContext().authentication = auth
        return ""
    }
}
