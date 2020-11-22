package am.gevorg.scope.dto.req;

import am.gevorg.scope.model.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogCreateRequest {

    private Date date;

    //need to convert Project DTO or MODEL
    private int projectId;

    private double hours;

}
