package org.acg.user.service

import org.acg.user.repository.PasswordResetTokenRepo
import org.acg.user.repository.RoleRepo
import org.acg.user.repository.UserRepo
import org.acg.user.repository.entity.PasswordResetToken
import org.acg.user.repository.entity.Role
import org.acg.user.repository.entity.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import java.util.HashSet

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepo: UserRepo
    @Autowired
    private lateinit var roleRepo: RoleRepo
    @Autowired
    private lateinit var passwordTokenRepo: PasswordResetTokenRepo
    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    override fun save(userInfo: UserInfo) {
        userInfo.token = bCryptPasswordEncoder.encode(userInfo.token)

        // DEFAULT ROLE is USER
        val roles = HashSet<Role>()
        roles.add(roleRepo.findByName("USER"))
        userInfo.roles = roles

        userRepo.save(userInfo)
    }

    override fun findByEmail(email: String): UserInfo? {
        return userRepo.findByEmail(email)
    }

    override fun createPasswordResetTokenForUser(userInfo: UserInfo, token: String) {
        val myToken = PasswordResetToken(token, userInfo)
        passwordTokenRepo.save(myToken)
    }

    override fun changeUserPassword(userInfo: UserInfo, password: String) {
        userInfo.token = bCryptPasswordEncoder.encode(password)
        userRepo.save(userInfo)
    }
}
