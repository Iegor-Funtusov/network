package ua.com.alevel.network.web.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.com.alevel.network.facade.AuthValidatorFacade;
import ua.com.alevel.network.facade.RegistrationFacade;
import ua.com.alevel.network.security.SecurityService;
import ua.com.alevel.network.web.controller.AbstractController;
import ua.com.alevel.network.web.data.request.AuthData;

/**
 * @author Iehor Funtusov, created 24/12/2020 - 11:17 AM
 */

@Controller
public class PersonalAuthController extends AbstractController {

    private final RegistrationFacade registrationFacade;
    private final AuthValidatorFacade authValidatorFacade;
    private final SecurityService personalSecurityService;

    public PersonalAuthController(RegistrationFacade registrationFacade, AuthValidatorFacade authValidatorFacade, SecurityService personalSecurityService) {
        this.registrationFacade = registrationFacade;
        this.authValidatorFacade = authValidatorFacade;
        this.personalSecurityService = personalSecurityService;
    }

    @GetMapping({"/", "/dashboard"})
    public String main() {
        return "page/personal/dashboard";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        if (personalSecurityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("authForm", new AuthData());
        return "page/personal/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("authForm") AuthData authForm, BindingResult bindingResult) {
        authValidatorFacade.validate(authForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "page/personal/registration";
        }
        registrationFacade.registration(authForm);
        personalSecurityService.autoLogin(authForm.getEmail(), authForm.getPasswordConfirm());
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (personalSecurityService.isAuthenticated()) {
            return "redirect:/";
        }
        if (error != null)
            model.addAttribute("error", "Your email and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "page/personal/login";
    }
}
