package projectdemo.com.democompany.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projectdemo.com.democompany.entity.DepartmentEntity;
import projectdemo.com.democompany.repository.DepartmentRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departRepo;

    private static final Logger LOGGER= LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/department")
    public String Department(Model m) {
        List<DepartmentEntity> list = departRepo.findAll();
        m.addAttribute("all_departments",list);
        return "department";
    }

    @GetMapping("/createDepartment")
    public String loadDepartment() {
        return "create_department";
    }

    @GetMapping("/editDepartment/{id}")
    public String editDepartment(@PathVariable(value = "id") long id, Model m) {
        Optional<DepartmentEntity> departments = departRepo.findById(id);
        DepartmentEntity department = departments.get();
        LOGGER.info("depart get: "+department);
        m.addAttribute("departments_update",department);
        return "update_department";
    }

    @PostMapping("/save_department")
    public String saveDepartment(@ModelAttribute DepartmentEntity depart, HttpSession session) {
        departRepo.save(depart);
        session.setAttribute("msg", "Department Added Successfully!!!");
        return "redirect:/user/department";
    }

    @PostMapping("/update_department/{id}")
    public String updateDepartment(@PathVariable(value = "id") long id ,@ModelAttribute DepartmentEntity depart, HttpSession session) {
        Optional<DepartmentEntity> departments = departRepo.findById(id);
        LOGGER.info("depart update: "+depart);
        DepartmentEntity department = departments.get(); //get id = 0 lỗi
        department.setDepartment_id(id);
        department.setDepartment_code(depart.getDepartment_code());
        department.setDepartment_name(depart.getDepartment_name());
        departRepo.save(department);
        session.setAttribute("msg", "Department Update Successfully!!!");
        return "redirect:/user/department";
    }

    @GetMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable(name = "id") long id, HttpSession session) {
        departRepo.deleteById(id);
        session.setAttribute("msg", "Department Delete Successfully!!!");
        return "redirect:/user/department";
    }
}
