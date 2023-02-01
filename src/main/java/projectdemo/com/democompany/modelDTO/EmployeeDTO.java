package projectdemo.com.democompany.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long employee_id;
    private String birthday;
    private String employee_code;
    private String position;
    private String images;
    private String gender;
    private String phone;
    private String timework;
    private String education;
    private String exp;
    private String expenglish;
    private String address;
    private String target;
    private String comments;
    private String fullname;
    private String email;
    private String departments_name;
}
