package projectdemo.com.democompany.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLE")
@Component
public class RoleEntity implements Serializable {
    private static final long serialVersionIUD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "Role_name")
    private String role_name;

    @Column(name = "role_code")
    private String role_code;


    public RoleEntity(String role_name, String role_code) {
        this.role_name = role_name;
        this.role_code = role_code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_code() {
        return role_code;
    }

    public void setRole_code(String role_code) {
        this.role_code = role_code;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", role_code='" + role_code + '\'' +
                '}';
    }
}
