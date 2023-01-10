package musicplayer.controller;

import musicplayer.entity.MasterGender;
import musicplayer.entity.User;
import musicplayer.entity.UserAddress;
import musicplayer.entity.UserRegApp;
import musicplayer.exception.ResourceNotFoundException;
import musicplayer.form.UserForm;
import musicplayer.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller

public class UserController {

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @Autowired
    private MasterGenderService masterGenderService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm(new User(),new UserAddress(),new UserAddress()));
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult, Model theModel, RedirectAttributes ra) {
        userValidator.validate(userForm.getUser(), bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        ra.addFlashAttribute("flashAttr", "User Application Created Successfully");
        theModel.addAttribute("SuccessMsg", "User Application Created Successfully");
        return "redirect:/login";
       // return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid/User is not active.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value =  "/success", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role =  authentication.getAuthorities().toString();

        if(role.contains("ROLE_SUPER_ADMIN")){
            model.addAttribute("message", "superadmin");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/active-users"));

        }
        else if(role.contains("ROLE_ADMIN")){
            model.addAttribute("message", "admin");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/user-reg-apps"));

        }
        else if(role.contains("ROLE_USER")) {
            model.addAttribute("message", "user");
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/song/list"));
        }
        return "welcome";
    }
    @ModelAttribute("genderDetailsList")
    public List<MasterGender> getGenderDetailsList()
    {
        List<MasterGender> genderDetailsList = this.masterGenderService.getGenders();

        return genderDetailsList;
    }



}