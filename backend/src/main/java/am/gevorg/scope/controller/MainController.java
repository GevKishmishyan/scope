package am.gevorg.scope.controller;

import am.gevorg.scope.dto.req.UserAuthRequest;
import am.gevorg.scope.dto.req.UserRegisterRequest;
import am.gevorg.scope.dto.resp.UserAuthResponse;
import am.gevorg.scope.exception.ModelAlreadyExistException;
import am.gevorg.scope.exception.ModelNotFoundException;
import am.gevorg.scope.model.User;
import am.gevorg.scope.service.UserService;
import am.gevorg.scope.util.JwtTokenUtil;
import am.gevorg.scope.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final String frontUrl = "http://localhost:4200/";

    @GetMapping("/")
    public RedirectView homePage() {
        return new RedirectView(frontUrl);
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) throws IOException, ModelAlreadyExistException {
        if (userService.isUserExist(userRegisterRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userService.register(userRegisterRequest);
        return ResponseEntity.ok(UserUtil.userToDto(user));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> auth(@RequestBody UserAuthRequest userAuthRequest, HttpServletRequest request) {
        User user = null;
        try {
            user = userService.findByEmail(userAuthRequest.getEmail());
        } catch (ModelNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        if (passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            request.getSession().setAttribute("token", token);
            return ResponseEntity.ok(UserAuthResponse.builder()
                    .userRegisterResponse(UserUtil.userToDto(user))
                    .token(token)
                    .build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

}