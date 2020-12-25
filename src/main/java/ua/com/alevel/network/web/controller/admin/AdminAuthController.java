package ua.com.alevel.network.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.alevel.network.security.SecurityService;
import ua.com.alevel.network.web.controller.AbstractController;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 10:21 AM
 */

@Controller
public class AdminAuthController extends AbstractController {

    private final SecurityService adminSecurityService;

    public AdminAuthController(SecurityService adminSecurityService) {
        this.adminSecurityService = adminSecurityService;
    }

    @GetMapping({"/admin", "/admin/dashboard"})
    public String main() {
        return "page/admin/dashboard";
    }

    @GetMapping("/admin/login")
    public String login(Model model, String error, String logout) {
        if (adminSecurityService.isAuthenticated()) {
            return "redirect:/admin";
        }
        if (error != null)
            model.addAttribute("error", "Your email and password is invalid.");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "page/admin/login";
    }
}
