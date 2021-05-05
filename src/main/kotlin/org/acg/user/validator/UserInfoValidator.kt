package org.acg.user.validator

import org.acg.user.domain.PasswordDto
import org.acg.user.repository.entity.UserInfo
import org.acg.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

@Component
class UserInfoValidator : Validator {

    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var passwordValidator: PasswordValidator

    override fun supports(aClass: Class<*>): Boolean =
        UserInfo::class == aClass

    override fun validate(o: Any, errors: Errors) {
        val userInfo = o as UserInfo

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty")
        errors.rejectValue("email", "Duplicate")

        // Validate Password
        val passwordDto = PasswordDto(userInfo.token, userInfo.confirmToken)
        passwordValidator.validate(passwordDto, errors)
    }
}
