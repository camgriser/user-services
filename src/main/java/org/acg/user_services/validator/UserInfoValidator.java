package org.acg.user_services.validator;

import org.acg.user_services.domain.PasswordDto;
import org.acg.user_services.repository.entity.UserInfo;
import org.acg.user_services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserInfoValidator implements Validator {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordValidator passwordValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserInfo.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserInfo userInfo = (UserInfo) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (userService.findByEmail(userInfo.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate");
        }

        // Validate Password
        PasswordDto passwordDto = new PasswordDto();
        passwordDto.setToken(userInfo.getToken());
        passwordDto.setConfirmToken(userInfo.getConfirmToken());
        passwordValidator.validate(passwordDto, errors);
    }
}
