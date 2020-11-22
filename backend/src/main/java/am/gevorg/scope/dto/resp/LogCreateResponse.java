package am.gevorg.scope.dto.resp;

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
public class LogCreateResponse {

    private Date date;
    private double hours;

    // send PROJECT DTO as LOGGED PROJECT to front in JSON
    private ProjectCreateResponse projectCreateResponse;
    // send USER DTO as CREATOR to front in JSON
    private UserRegisterResponse userRegisterResponse;

}
