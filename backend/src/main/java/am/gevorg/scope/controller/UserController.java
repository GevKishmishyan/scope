package am.gevorg.scope.controller;

import am.gevorg.scope.dto.resp.FileResponse;
import am.gevorg.scope.model.User;
import am.gevorg.scope.model.enums.Role;
import am.gevorg.scope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${image.upload.dir}")
    private String uploadDir;

    @RequestMapping(value = "/team-members", method = RequestMethod.GET)
    private List<User> getAllTeamMembers() {
        return userService.findByRole(Role.TEAM_MEMBER);
    }

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    private ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<?> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private void deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/get-profile-pic/{picture}", method = RequestMethod.GET)
    public void getProfilePicture(HttpServletResponse response, @PathVariable String picture) throws IOException {
        userService.getProfilePicture(response, picture);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadProfilePicture(@RequestParam MultipartFile file) throws IOException {
        String name = userService.uploadProfilePicture(file);
        return ResponseEntity.ok(FileResponse.builder()
                .name(name)
                .build());
    }

}
