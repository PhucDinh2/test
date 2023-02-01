package projectdemo.com.democompany.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductRequest {
    private Long product_id;
    private Long products_code;
    private String products_name;
    private String enterprise;
    private Boolean status;
    private List<Long> list_employees;
    private String contents;
    private String time_start;
    private String time_end;
    private String comments;

}
