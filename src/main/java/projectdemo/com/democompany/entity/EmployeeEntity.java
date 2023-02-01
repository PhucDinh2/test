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
@Table(name = "EMPLOYEE")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionIUD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employee_id;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "employee_code")
    private String employee_code;

    @Column(name = "images")
    private String images;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "timework")
    private String timework;

    @Column(name = "position")
    private String position;

    @Column(name = "education")
    private String education;

    @Column(name = "exp")
    private String exp;

    @Column(name = "expenglish")
    private String expenglish;

    @Column(name = "address")
    private String address;

    @Column(name = "target")
    private String target;

    @Column(name = "comments")
    private String comments;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "employees")
    private List<ProductsEntity> products;

    public EmployeeEntity(String birthday, String employee_code, String images, String gender, String phone, String timework, String position, String education, String exp, String expenglish, String address, String target, String comments, UserEntity user, List<ProductsEntity> products) {
        this.birthday = birthday;
        this.employee_code = employee_code;
        this.images = images;
        this.gender = gender;
        this.phone = phone;
        this.timework = timework;
        this.position = position;
        this.education = education;
        this.exp = exp;
        this.expenglish = expenglish;
        this.address = address;
        this.target = target;
        this.comments = comments;
        this.user = user;
        this.products = products;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimework() {
        return timework;
    }

    public void setTimework(String timework) {
        this.timework = timework;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getExpenglish() {
        return expenglish;
    }

    public void setExpenglish(String expenglish) {
        this.expenglish = expenglish;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(String employee_code) {
        this.employee_code = employee_code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<ProductsEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsEntity> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "employee_id=" + employee_id +
                ", birthday='" + birthday + '\'' +
                ", employee_code='" + employee_code + '\'' +
                ", images='" + images + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", timework='" + timework + '\'' +
                ", position='" + position + '\'' +
                ", education='" + education + '\'' +
                ", exp='" + exp + '\'' +
                ", expenglish='" + expenglish + '\'' +
                ", address='" + address + '\'' +
                ", target='" + target + '\'' +
                ", comments='" + comments + '\'' +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
