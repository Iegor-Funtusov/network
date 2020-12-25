package ua.com.alevel.network.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Iehor Funtusov, created 25/12/2020 - 10:59 AM
 */
public abstract class AbstractController {

    protected void showInfo(Model model, String message) {
        model.addAttribute("message", message);
    }

    protected void showInfo(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("message", message);
    }

    protected void showError(Model model, String message) {
        model.addAttribute("errorMessage", message);
    }

    protected void showError(RedirectAttributes redirectAttributes, String error) {
        redirectAttributes.addFlashAttribute("errorMessage", error);
    }

    protected void showWarn(Model model, String message) {
        model.addAttribute("warnMessage", message);
    }

    protected void showWarn(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("warnMessage", message);
    }
}
