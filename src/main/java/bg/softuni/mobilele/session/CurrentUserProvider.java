package bg.softuni.mobilele.session;

import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public CurrentUserProvider(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void loadCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
            return;
        }

        String userName = auth.getName();

        userRepository.findByUsername(userName).ifPresent(user -> {
            currentUser.setUsername(user.getUsername());
            currentUser.setFullName(user.getFirstName() + " " + user.getLastName());
            currentUser.setRole(user.getRole().getUserRole().name());
            currentUser.setUserLogged(true);
        });
    }

}

