package projectdemo.com.democompany.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {
    private Long product_id;
    private Long products_code;
    private String products_name;
    private String enterprise;
    private Boolean status;
    private String contents;
    private String time_start;
    private String time_end;
    private String comments;
    private String fullname;
    private Long employee_id;
    private List<Long> list_employees;
    private String employee_code;
    private String phone;
    private String email;
    private String departments_name;
    private String position;
    private String images;
}
