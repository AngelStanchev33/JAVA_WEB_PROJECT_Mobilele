package bg.softuni.mobilele.session;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributeConfig {

    private final CurrentUser currentUser;

    public GlobalModelAttributeConfig(CurrentUser currentUser, CurrentUserProvider currentUserProvider) {
        this.currentUser = currentUser;
    }

    @ModelAttribute("currentUser")
    public CurrentUser currentUser() {
        return currentUser;
    }
}
