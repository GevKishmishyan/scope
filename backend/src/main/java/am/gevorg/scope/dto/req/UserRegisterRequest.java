package am.gevorg.scope.dto.req;

import am.gevorg.scope.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;

}
