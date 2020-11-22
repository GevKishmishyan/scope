package am.gevorg.scope.service;

import am.gevorg.scope.dto.req.LogCreateRequest;
import am.gevorg.scope.model.Log;
import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import am.gevorg.scope.repository.LogRepository;
import am.gevorg.scope.repository.ProjectRepository;
import am.gevorg.scope.security.AuthSecurityService;
import am.gevorg.scope.util.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final ProjectRepository projectRepository;
    private final AuthSecurityService authSecurityService;
    private final ProjectService projectService;

    public Log create(LogCreateRequest logCreateRequest) {
        User user = authSecurityService.getCurrentUser();
        Project project = projectService.findById(logCreateRequest.getProjectId());
        project.setHours(project.getHours() + logCreateRequest.getHours());
        projectRepository.save(project);
        return logRepository.save(LogUtil.dtoToLog(logCreateRequest, user, project));
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    public List<Log> getAllLogsPerUser(User user) {
        return logRepository.findAllByCreatedBy(user);
    }

    public Log findById(int id) {
        return logRepository.findById(id).orElse(null);
    }

    public void deleteById(int id) { logRepository.deleteById(id); }

    public List<Log> findAllByProjectId(int projectId) {
        return logRepository.findAllByProjectId(projectId);
    }

}
