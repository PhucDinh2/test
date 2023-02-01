package projectdemo.com.democompany.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import projectdemo.com.democompany.modelDTO.UserDTO;
import projectdemo.com.democompany.repository.CustomUserRepo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomUserRepoImpl implements CustomUserRepo {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<UserDTO> findAccount() {

        List<Object[]> res = entityManager.createNativeQuery("SELECT u.user_id as id, u.fullname as fullname, u.email as email, u.role_id as role_id, u.status as status, u.comments as comments, d.departments_name as departments_name FROM user u JOIN department d ON d.department_id = u.department_id").getResultList();
        List<UserDTO> resDto = new ArrayList<>();
        for(Object[] o : res) {
            UserDTO u = new UserDTO();
            u.setUser_id(Long.valueOf(o[0].toString()));
            u.setFullname(o[1].toString());
            u.setEmail(o[2].toString());
            u.setRole_id(o[3].toString());
            u.setStatus((Boolean) o[4]);
            u.setComments((String) o[5]);
            u.setDepartments_name(o[6].toString());
            resDto.add(u);
        }

        return resDto;
    }
}
