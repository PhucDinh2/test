package projectdemo.com.democompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import projectdemo.com.democompany.entity.DepartmentEntity;
import projectdemo.com.democompany.entity.EmployeeEntity;
import projectdemo.com.democompany.entity.UserEntity;
import projectdemo.com.democompany.repository.DepartmentRepository;
import projectdemo.com.democompany.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/login")
    public String pageLogin() {
        return "index";
    }

    @GetMapping("/register")
    public String pageRegister() {
        return "auth_register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity user, HttpSession session) {
        EmployeeEntity emp = new EmployeeEntity();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole_id("ROLE_USER");
        user.setStatus(false);
        Optional<DepartmentEntity> d = departmentRepository.findById(1L);
        if(d.isPresent()) {
            user.setDepartment(d.get());
        }

        user.setFullname(user.getFullname());
        emp.setUser(user);
        user.setEmployee(emp);
//        e.setUser(user);
//        user.setEmployee(e);
        repo.save(user);
        session.setAttribute("message", "User Register Sucessfully!!!");
        return "redirect:/register";
    }
}
