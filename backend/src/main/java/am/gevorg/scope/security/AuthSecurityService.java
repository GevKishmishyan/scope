package am.gevorg.scope.security;


import am.gevorg.scope.model.User;

@FunctionalInterface
public interface AuthSecurityService {
    User getCurrentUser();
}
