package projectdemo.com.democompany.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
private Long user_id;
private String fullname;
private String email;
private String role_id;
private Boolean status;
private String departments_name;
private String comments;
}
