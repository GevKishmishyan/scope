package am.gevorg.scope.util;

import am.gevorg.scope.dto.req.LogCreateRequest;
import am.gevorg.scope.dto.resp.LogCreateResponse;
import am.gevorg.scope.model.Log;
import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogUtil {

    private final ProjectUtil projectUtil;

    public static Log dtoToLog(LogCreateRequest log){
        return Log.builder()
                .date(log.getDate())
                .hours(log.getHours())
                .build();
    }

    public static Log dtoToLog(LogCreateRequest log, User createdBy, Project project){
        return Log.builder()
                .date(log.getDate())
                .project(project)
                .createdBy(createdBy)
                .hours(log.getHours())
                .build();
    }

    public LogCreateResponse logToDto(Log log){
        return LogCreateResponse.builder()
                .date(log.getDate())
                .projectCreateResponse(projectUtil.projectToDto(log.getProject()))
                .userRegisterResponse(UserUtil.userToDto(log.getCreatedBy()))
                .build();
    }

}
