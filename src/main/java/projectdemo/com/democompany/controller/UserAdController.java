package projectdemo.com.democompany.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projectdemo.com.democompany.entity.DepartmentEntity;
import projectdemo.com.democompany.entity.EmployeeEntity;
import projectdemo.com.democompany.entity.RoleEntity;
import projectdemo.com.democompany.entity.UserEntity;
import projectdemo.com.democompany.modelDTO.UserDTO;
import projectdemo.com.democompany.repository.DepartmentRepository;
import projectdemo.com.democompany.repository.RoleRepository;
import projectdemo.com.democompany.repository.UserRepository;
import projectdemo.com.democompany.request.UserResgisterRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserAdController {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserAdController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/Account")
    public String account(Model m) {
        List<UserDTO> list = userRepository.findAccount();
        LOGGER.info("List user with detail information");
        m.addAttribute("all_users",list);
        return "management_account";
    }

    @GetMapping("/createAccount")
    public String createAccount(Model m) {
        List<RoleEntity> roles = roleRepo.findAll();
        LOGGER.info("List role with information");
        m.addAttribute("all_roles",roles);
        List<DepartmentEntity> departs = departRepo.findAll();
        LOGGER.info("List department with information");
        m.addAttribute("all_departments",departs);
        return "create_account";
    }

    @GetMapping("/editAccount/{id}")
    public String editAccount(@PathVariable(value = "id") long id, Model m) {
        List<DepartmentEntity> departs = departRepo.findAll();
        Optional<UserEntity> users = userRepository.findById(id);
        List<RoleEntity> roles = roleRepo.findAll();
        UserEntity user = users.get();
        m.addAttribute("all_departments",departs);
        m.addAttribute("account_update",user);
        m.addAttribute("all_roles",roles);
        return "update_account";
    }

    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute UserResgisterRequest user, @ModelAttribute UserEntity users, HttpSession session) {
        LOGGER.info("Save Users: " +users);
        UserEntity userEntity = new UserEntity();
        EmployeeEntity emp = new EmployeeEntity();
        userEntity.setFullname(user.getFullname());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setStatus(user.getStatus());
        Optional<DepartmentEntity> d = departRepo.findById(user.getDepartment_id());
        if(d.isPresent()) {
            userEntity.setDepartment(d.get());
        }
        userEntity.setRole_id(user.getRole_id());
        userEntity.setComments(user.getComments());
        userEntity.setEmployee(emp);
        emp.setUser(userEntity);
        userRepository.save(userEntity);
        session.setAttribute("msg", "Account Added Successfully!!!");
        return "redirect:/user/Account";
    }

    @PostMapping("/updateAccount/{id}")
    public String updateAccount(@PathVariable(value = "id") long id ,@ModelAttribute UserResgisterRequest user, HttpSession session) {
        Optional<UserEntity> userEnti = userRepository.findById(id);
        UserEntity users = userEnti.get();
        users.setUser_id(id);
        users.setFullname(user.getFullname());
        users.setEmail(user.getEmail());
        users.setPassword(encoder.encode(user.getPassword()));
        users.setStatus(user.getStatus());
        Optional<DepartmentEntity> d = departRepo.findById(user.getDepartment_id());
        if (d.isPresent()) {
            DepartmentEntity test = d.get();
            users.setDepartment(test);
        }
        users.setRole_id(user.getRole_id());
        users.setComments(user.getComments());
        userRepository.save(users);
        session.setAttribute("msg", "Account Update Successfully!!!");
        return "redirect:/user/Account";
    }

    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable(value = "id") long id, HttpSession session) {
        userRepository.deleteById(id);
        LOGGER.info("delete user with id " + id);
        session.setAttribute("msg", "Account Delete Successfully!!!");
        return "redirect:/user/Account";
    }
}
