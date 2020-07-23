package org.acg.user_services.validator;

import org.acg.user_services.domain.PasswordDto;
import org.acg.user_services.repository.entity.PasswordResetToken;
import org.acg.user_services.repository.entity.UserInfo;
import org.acg.user_services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserInfo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordDto passwordDto = (PasswordDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "token", "NotEmpty");
        if (passwordDto.getToken().length() < 8) {
            errors.rejectValue("token", "Size");
        }

        if (!passwordDto.getConfirmToken().equals(passwordDto.getToken())) {
            errors.rejectValue("confirmToken", "Diff");
        }
    }
}
