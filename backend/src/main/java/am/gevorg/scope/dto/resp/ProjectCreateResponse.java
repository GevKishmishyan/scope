package am.gevorg.scope.dto.resp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateResponse {

    private String name;
    private Date date;
    private Date deadline;

    // send USER DTO list as MEMBER to front in JSON
    private List<UserRegisterResponse> members;
    // send USER DTO as CREATOR to front in JSON
    private UserRegisterResponse userRegisterResponse;

    private double hours;

}
