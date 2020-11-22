package am.gevorg.scope.util;

import am.gevorg.scope.dto.req.ProjectCreateRequest;
import am.gevorg.scope.dto.resp.ProjectCreateResponse;
import am.gevorg.scope.model.Log;
import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import am.gevorg.scope.repository.LogRepository;
import am.gevorg.scope.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectUtil {

    private final LogService logService;

    public static Project dtoToProject(ProjectCreateRequest project) {
        return Project.builder()
                .name(project.getName())
                .date(project.getDate())
                .deadline(project.getDeadline())
                .build();
    }

    public static Project dtoToProject(ProjectCreateRequest project, List<User> members, User createdBy) {
        return Project.builder()
                .name(project.getName())
                .date(project.getDate())
                .deadline(project.getDeadline())
                .members(members)
                .createdBy(createdBy)
                .build();
    }

    public ProjectCreateResponse projectToDto(Project project) {
        double projectLogedHours = 0;
        List<Log> allLogsByBrojectId = logService.findAllByProjectId(project.getId());
        if (allLogsByBrojectId != null) {
            for (Log log : allLogsByBrojectId) {
                projectLogedHours += log.getHours();
            }
        }
        return ProjectCreateResponse.builder()
                .name(project.getName())
                .date(project.getDate())
                .deadline(project.getDeadline())
                .members(project.getMembers().stream().map(UserUtil::userToDto).collect(Collectors.toList()))
                .userRegisterResponse(UserUtil.userToDto(project.getCreatedBy()))
                .hours(projectLogedHours)
                .build();
    }

}
