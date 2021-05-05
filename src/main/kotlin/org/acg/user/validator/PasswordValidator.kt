package org.acg.user.validator;

import org.acg.user.domain.PasswordDto;
import org.acg.user.repository.entity.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
class PasswordValidator : Validator {

    override fun supports(aClass: Class<*>): Boolean =
        UserInfo::class == aClass

    override fun validate(o: Any, errors: Errors) {
        val passwordDto = o as PasswordDto

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "token", "NotEmpty");
        if (passwordDto.token.length < 8) {
            errors.rejectValue("token", "Size");
        }

        if (!passwordDto.confirmToken.equals(passwordDto.token)) {
            errors.rejectValue("confirmToken", "Diff");
        }
    }
}
