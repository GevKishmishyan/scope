package am.gevorg.scope.dto.resp;

import am.gevorg.scope.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterResponse {

    private String name;
    private String surname;
    private String email;
    private String profilePicture;
    private Role role;

}
