package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.mobilele.service.UserService;
import bg.softuni.mobilele.model.bindingModel.UserRegisterBindingModel;
import bg.softuni.mobilele.model.dto.UserRoleDto;

import org.springframework.ui.Model;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/users")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserRegisterBindingModel());
        }

        model.addAttribute("userRoles", userService.getAllRoles().getRoles());
    
        return "auth-register";
    }

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/register")
    public String postMethodName(@Valid @ModelAttribute("user") UserRegisterBindingModel dto,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", dto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);

            return "redirect:/users/register";
        }

        userService.registerUser(dto);

        return "redirect:/users/login";
    }
}
