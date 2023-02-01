package projectdemo.com.democompany.controller;

import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projectdemo.com.democompany.entity.EmployeeEntity;
import projectdemo.com.democompany.modelDTO.EmployeeDTO;
import projectdemo.com.democompany.repository.EmployeeRepository;
import projectdemo.com.democompany.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class EmployeeController {

    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    ServletContext app;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/employee")
    public String loadEmployee(Model m) {
        List<EmployeeDTO> list = employeeRepo.findEmployee();
        LOGGER.info("listttttt"+list.size());
        LOGGER.info(list.toString());
        m.addAttribute("All_employee", list);
        return "management_staff";
    }

    @GetMapping("/employee/{id}")
    public String profileEmployee(@PathVariable(value = "id") long id, Model m) {
        EmployeeDTO list = employeeRepo.findByIdEmployee(id);
        LOGGER.info("lisst123" + list);
        m.addAttribute("All_employee", list);
        return "staff_profile";
    }

    @GetMapping("/detailEmployee/{id}")
    public String detailEmployee(@PathVariable(value = "id") long id, Model m) {
        EmployeeDTO list = employeeRepo.findByIdEmployee(id);
        LOGGER.info("list: " + list);
        System.out.println("List 2" + list);
        m.addAttribute("All_employee", list);
        return "update_infomation_user";
    }

    @PostMapping("/detailEmployee/{id}")
    public String updateEmployee(@PathVariable(value = "id") long id ,@ModelAttribute EmployeeEntity employee, HttpSession session,@RequestParam(name = "imagesFile") MultipartFile imagefile) {
//        LOGGER.info("Employee update: "+employee);
//        LOGGER.info("ID: "+id);
//        LOGGER.info("ID check: " +employee.getEmployee_id());
        Optional<EmployeeEntity> employees1 = employeeRepo.findById(id);

        EmployeeEntity employees = employees1.get();
        employees.setEmployee_id(id);
        employees.setEmployee_code(employee.getEmployee_code());
        employees.setBirthday(employee.getBirthday());
        employees.setGender(employee.getGender());
        employees.setTimework(employee.getTimework());
        employees.setPosition(employee.getPosition());
        employees.setPhone(employee.getPhone());
        employees.setAddress(employee.getAddress());
        employees.setEducation(employee.getEducation());
        String originalFilename = imagefile.getOriginalFilename();
        int lastIndex = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(lastIndex);
        String avatarFilename = System.currentTimeMillis() + ext;
        File newfile = new File("D:\\demoCompany\\src\\main\\resources\\static\\images\\upload\\" + avatarFilename);
//        File newfile = new File(path + avatarFilename);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(newfile);
            fileOutputStream.write(imagefile.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        employees.setImages(avatarFilename);
        employees.setExp(employee.getExp());
        employees.setExpenglish(employee.getExpenglish());
        employees.setTarget(employee.getTarget());
        employees.setComments(employee.getComments());
        employeeRepo.save(employees);
        session.setAttribute("msg","Profile Update Successfully!!!");
        return "redirect:/user/employee";
    }
}
