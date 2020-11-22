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
public class UserAuthResponse {

    private String token;

    // send USER DTO as AUTHENTICATED USER to front in JSON
    private UserRegisterResponse userRegisterResponse;

}
