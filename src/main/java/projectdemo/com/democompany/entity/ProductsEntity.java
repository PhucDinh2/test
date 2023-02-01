package projectdemo.com.democompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "PRODUCTS")
public class ProductsEntity implements Serializable {
    private static final long serialVersionIUD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "products_code")
    private Long products_code;

    @Column(name = "products_name")
    private String products_name;

    @Column(name = "enterprise")
    private String enterprise;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "contents")
    private String contents;

    @Column(name = "time_start")
    private String time_start;

    @Column(name = "time_end")
    private String time_end;

    @Column(name = "comments")
    private String comments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "employees_products", joinColumns = {
            @JoinColumn(name = "product_id", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "employee_id", nullable = false, updatable = false)})
    private List<EmployeeEntity> employees;

    public ProductsEntity(Long products_code, String products_name, String enterprise, Boolean status, String contents, String time_start, String time_end, String comments, List<EmployeeEntity> employees) {
        this.products_code = products_code;
        this.products_name = products_name;
        this.enterprise = enterprise;
        this.status = status;
        this.contents = contents;
        this.time_start = time_start;
        this.time_end = time_end;
        this.comments = comments;
        this.employees = employees;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProducts_name() {
        return products_name;
    }

    public void setProducts_name(String products_name) {
        this.products_name = products_name;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getProducts_code() {
        return products_code;
    }

    public void setProducts_code(Long products_code) {
        this.products_code = products_code;
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "ProductsEntity{" +
                "product_id=" + product_id +
                ", products_code=" + products_code +
                ", products_name='" + products_name + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", status=" + status +
                ", contents='" + contents + '\'' +
                ", time_start='" + time_start + '\'' +
                ", time_end='" + time_end + '\'' +
                ", comments='" + comments + '\'' +
                ", employees=" + employees +
                '}';
    }
}
