package projectdemo.com.democompany.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResgisterRequest {
    private String fullname;
    private String email;
    private String password;
    private Long department_id;
    private Boolean status;
    private String role_id;
    private String comments;
}
