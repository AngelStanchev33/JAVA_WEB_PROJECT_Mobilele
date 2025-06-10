package bg.softuni.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class LoginController {

    @GetMapping("/login")
    public String getMethodName() {
        return "auth-login";
    }

    @PostMapping
    public String loginError(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", true);
        return "auth-login";
    }
}
