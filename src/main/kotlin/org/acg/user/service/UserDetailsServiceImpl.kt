package org.acg.user.service

import org.acg.user.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.HashSet
import kotlin.jvm.Throws

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var userRepo: UserRepo

    @Override
    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String)  : UserDetails {
        val userInfo = userRepo.findByEmail(email) ?: throw UsernameNotFoundException("The user was not found!")

        val grantedAuthoritySet = HashSet<SimpleGrantedAuthority>()
        userInfo.roles.forEach {
            grantedAuthoritySet.add(SimpleGrantedAuthority(it.name))
        }

        return User(userInfo.email, userInfo.token, grantedAuthoritySet)
    }

}
