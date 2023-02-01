package projectdemo.com.democompany.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import projectdemo.com.democompany.modelDTO.EmployeeDTO;
import projectdemo.com.democompany.repository.CustomEmployeeRepo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomEmployeeRepoImpl implements CustomEmployeeRepo {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<EmployeeDTO> findEmployee() {
        List<Object[]> res = entityManager.createNativeQuery("SELECT e.employee_id as employee_id, e.birthday as birthday, e.employee_code as employee_code, e.position as position, e.images as images, e.gender as gender, e.phone as phone, e.timework as timework, e.education as education, e.exp as exp, e.expenglish as expenglish, e.address as address, e.target as target, e.comments as comments, u.fullname as fullname, u.email as email,d.departments_name as departments_name FROM user u JOIN employee e ON e.user_id = u.user_id JOIN department d ON u.department_id = d.department_id").getResultList();
        List<EmployeeDTO> resDTO = new ArrayList<>();
        for (Object[] o : res) {
            EmployeeDTO e = new EmployeeDTO();
            e.setEmployee_id(Long.valueOf(o[0].toString()));
            e.setBirthday((String) o[1]);
            e.setEmployee_code((String) o[2]);
            e.setPosition((String) o[3]);
            e.setImages((String) o[4]);
            e.setGender((String) o[5]);
            e.setPhone((String) o[6]);
            e.setTimework((String) o[7]);
            e.setEducation((String) o[8]);
            e.setExp((String) o[9]);
            e.setExpenglish((String) o[10]);
            e.setAddress((String) o[11]);
            e.setTarget((String) o[12]);
            e.setComments((String) o[13]);
            e.setFullname((String) o[14]);
            e.setEmail(o[15].toString());
            e.setDepartments_name(o[16].toString());
            resDTO.add(e);
        }
        return resDTO;
    }

    @Override
    public EmployeeDTO findByIdEmployee(long id) {
        List<Object[]> res = entityManager.createNativeQuery("SELECT e.employee_id as employee_id, e.birthday as birthday, e.employee_code as employee_code, e.position as position, e.images as images, e.gender as gender, e.phone as phone, e.timework as timework, e.education as education, e.exp as exp, e.expenglish as expenglish, e.address as address, e.target as target, e.comments as comments, u.fullname as fullname, u.email as email,d.departments_name as departments_name FROM user u JOIN employee e ON e.user_id = u.user_id JOIN department d ON u.department_id = d.department_id WHERE e.employee_id = " + id).getResultList();
        if(res.size() > 0) {
            EmployeeDTO e = new EmployeeDTO();
            e.setEmployee_id(Long.valueOf(res.get(0)[0].toString()));
            e.setBirthday((String) res.get(0)[1]);
            e.setEmployee_code((String) res.get(0)[2]);
            e.setPosition((String) res.get(0)[3]);
            e.setImages((String) res.get(0)[4]);
            e.setGender((String) res.get(0)[5]);
            e.setPhone((String) res.get(0)[6]);
            e.setTimework((String) res.get(0)[7]);
            e.setEducation((String) res.get(0)[8]);
            e.setExp((String) res.get(0)[9]);
            e.setExpenglish((String) res.get(0)[10]);
            e.setAddress((String) res.get(0)[11]);
            e.setTarget((String) res.get(0)[12]);
            e.setComments((String) res.get(0)[13]);
            e.setFullname((String) res.get(0)[14]);
            e.setEmail(res.get(0)[15].toString());
            e.setDepartments_name(res.get(0)[16].toString());
            return e;
        }
        return null;
    }
}
