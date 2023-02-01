package projectdemo.com.democompany.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Table(name = "USER")
public class UserEntity implements Serializable {
    private static final long serialVersionIUD = 1L;

    @Id
    @JsonSerialize
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "role_id")
    private String role_id;

    @Column(name = "comments")
    private String comments;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private EmployeeEntity employee;

    @ManyToOne()
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    public UserEntity(String fullname, String email, String password, Boolean status, String role_id, String comments, EmployeeEntity employee, DepartmentEntity department) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role_id = role_id;
        this.comments = comments;
        this.employee = employee;
        this.department = department;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "user_id=" + user_id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role_id='" + role_id + '\'' +
                ", comments='" + comments + '\'' +
                ", employee=" + employee +
                ", department=" + department +
                '}';
    }
}
