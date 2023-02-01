package projectdemo.com.democompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "DEPARTMENT")
public class DepartmentEntity implements Serializable {
    private static final long serialVersionIUD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private long department_id;

    @Column(name = "Departments_name")
    private String department_name;

    @Column(name = "department_code")
    private String department_code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    @JsonIgnore
    private Collection<UserEntity> userEntity;

    public DepartmentEntity(String department_name, String department_code, Collection<UserEntity> userEntity) {
        this.department_name = department_name;
        this.department_code = department_code;
        this.userEntity = userEntity;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public Collection<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(Collection<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                ", department_code='" + department_code + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }
}
