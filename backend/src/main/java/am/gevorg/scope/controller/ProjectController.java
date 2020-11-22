package am.gevorg.scope.controller;

import am.gevorg.scope.dto.req.ProjectCreateRequest;
import am.gevorg.scope.exception.ModelNotFoundException;
import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import am.gevorg.scope.model.enums.Role;
import am.gevorg.scope.security.AuthSecurityService;
import am.gevorg.scope.service.ProjectService;
import am.gevorg.scope.util.ProjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final AuthSecurityService authSecurityService;
    private final ProjectUtil projectUtil;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProjectCreateRequest projectCreateRequest) {
        if (projectService.isProjectExist(projectCreateRequest.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Project project = projectService.create(projectCreateRequest);
        return ResponseEntity.ok(projectUtil.projectToDto(project));
    }

    @RequestMapping(value = "/all-projects", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @RequestMapping(value = "/team-member-projects", method = RequestMethod.GET)
    public ResponseEntity<?> getAllProjectsPerUser() {
        User userById = authSecurityService.getCurrentUser();
        if (userById.getRole() == Role.TEAM_MEMBER) {
            return ResponseEntity.ok(projectService.getAllProjectsPerUser(userById));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user with Role TEAM_MEMBER has been authenticated.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Project> findById(@PathVariable int id) {
        return ResponseEntity.ok(projectService.findById(id));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable int id) throws ModelNotFoundException {
        projectService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> getCurrentUserProjects(){
        return ResponseEntity.ok(projectService.getAllProjectsPerUser(authSecurityService.getCurrentUser()));
    }
}
