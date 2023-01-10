package musicplayer.service;

import musicplayer.entity.User;

import musicplayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "User.username", "NotEmpty");
//        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
//            errors.rejectValue("User.username", "Size.userForm.username");
//        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("User.username", "Duplicate.userForm.User.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "User.password", "NotEmpty");
//        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//            errors.rejectValue("User.password", "Size.userForm.password");
//        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("User.passwordConfirm", "Diff.userForm.User.passwordConfirm");
        }
    }
}