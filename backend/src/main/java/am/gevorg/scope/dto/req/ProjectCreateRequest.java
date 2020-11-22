package am.gevorg.scope.dto.req;

import am.gevorg.scope.model.User;
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
public class ProjectCreateRequest {

    private String name;
    private Date date;
    private Date deadline;

    //need to convert USER MODEL or DTO
    private List<Integer> membersId;

}
