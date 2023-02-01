package projectdemo.com.democompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projectdemo.com.democompany.entity.UserEntity;
import projectdemo.com.democompany.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("")
    public String home(Principal p, Model m) {
        String name =p.getName();
        UserEntity user = userRepo.findByEmail(name);
        m.addAttribute("fullName",user.getFullname());

        return "dashboard";
    }
}
