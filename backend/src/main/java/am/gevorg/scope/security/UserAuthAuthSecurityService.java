package am.gevorg.scope.security;


import am.gevorg.scope.exception.UnauthorizedException;
import am.gevorg.scope.model.User;
import am.gevorg.scope.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserAuthAuthSecurityService implements UserDetailsService, AuthSecurityService {

    private final UserRepository userRepository;

    public UserAuthAuthSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with ' %s ' E-MAIL did not found", email)));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public User getCurrentUser() {
        String loggedInUserEmail = getLoggedInUserEmail();
        return userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new UnauthorizedException("Can't find any authenticated user on this request"));
    }

    private String getLoggedInUserEmail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof org.springframework.security.core.userdetails.User)
                return ((org.springframework.security.core.userdetails.User) principal).getUsername();
        }

        throw new UnauthorizedException("Can't find any authenticated user on this request");
    }
}
