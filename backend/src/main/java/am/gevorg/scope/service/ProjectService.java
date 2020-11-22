package am.gevorg.scope.service;

import am.gevorg.scope.dto.req.ProjectCreateRequest;
import am.gevorg.scope.exception.ModelNotFoundException;
import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import am.gevorg.scope.repository.LogRepository;
import am.gevorg.scope.repository.ProjectRepository;
import am.gevorg.scope.security.AuthSecurityService;
import am.gevorg.scope.util.ProjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final AuthSecurityService authSecurityService;
    private final LogRepository logRepository;

    public Project create(ProjectCreateRequest projectCreateRequest) {
        User user = authSecurityService.getCurrentUser();
        List<User> members = new ArrayList<>();
        for (Integer integer : projectCreateRequest.getMembersId()) {
            User byId = userService.findUserById(integer);
            members.add(byId);
        }
        Project project = ProjectUtil.dtoToProject(projectCreateRequest, members, user);
        return projectRepository.save(project);
    }

    public boolean isProjectExist(String name) {
        return projectRepository.existsByName(name);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getAllProjectsPerUser(User user) {
        return projectRepository.findAllByMembersContains(user);
    }

    public Project findById(int id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) throws ModelNotFoundException {
        Optional<Project> byId = projectRepository.findById(id);
        if (byId.isPresent()){
            projectRepository.deleteById(id);
        } else {
            throw new ModelNotFoundException("Project does not exist.");
        }
    }

    public Optional<Project> findByName(String name){
        return projectRepository.findByName(name);
    }
}
