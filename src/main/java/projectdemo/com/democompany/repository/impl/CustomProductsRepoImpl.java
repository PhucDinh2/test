package projectdemo.com.democompany.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import projectdemo.com.democompany.modelDTO.ProductsDTO;
import projectdemo.com.democompany.repository.CustomProductsRepo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomProductsRepoImpl implements CustomProductsRepo {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<ProductsDTO> findProducts() {
        List<Object[]> res = entityManager.createNativeQuery("SELECT DISTINCT p.product_id as product_id, p.products_code as products_code, p.products_name as products_name, p.enterprise as enterprise, p.status as status, p.contents as contents, p.time_start as time_start, p.time_end as time_end, p.comments as comments, u.fullname as fullname, e.employee_id as employee_id, e.employee_code as employee_code, e.phone as phone, u.email as email, d.departments_name as departments_name, e.position as position, e.images as images FROM products p JOIN employees_products ep ON ep.product_id = p.product_id JOIN employee e ON e.employee_id = ep.employee_id JOIN user u ON  e.user_id = u.user_id JOIN department d ON u.department_id = d.department_id").getResultList();
        List<ProductsDTO> resDTO = new ArrayList<>();
        Map idObject = new HashMap();
        for (Object[] o : res) {
            ProductsDTO p = new ProductsDTO();
            p.setProduct_id(Long.valueOf(o[0].toString()));
            p.setProducts_code(Long.valueOf(o[1].toString()));
            p.setProducts_name((String) o[2]);
            p.setEnterprise((String) o[3]);
            p.setStatus((Boolean) o[4]);
            p.setContents((String) o[5]);
            p.setTime_start((String) o[6]);
            p.setTime_end((String) o[7]);
            p.setComments((String) o[8]);
            p.setFullname((String) o[9]);
            p.setEmployee_id(Long.valueOf(o[10].toString()));
            p.setEmployee_code((String) o[11]);
            p.setPhone((String) o[12]);
            p.setEmail((String) o[13]);
            p.setDepartments_name((String) o[14]);
            p.setPosition((String) o[15]);
            p.setImages((String) o[16]);
            if (!idObject.containsKey(Long.valueOf(o[0].toString()))) {
                idObject.put(Long.valueOf(o[0].toString()), Long.valueOf(o[1].toString()));
                resDTO.add(p);
            }
        }

        return resDTO;
    }


    @Override
    public ProductsDTO findByIdProducts(long id) {
        List<Object[]> res = entityManager.createNativeQuery("SELECT p.product_id as product_id, p.products_code as products_code, p.products_name as products_name, p.enterprise as enterprise, p.status as status, p.contents as contents, p.time_start as time_start, p.time_end as time_end, p.comments as comments, u.fullname as fullname, e.employee_id as employee_id, e.employee_code as employee_code, e.phone as phone, u.email as email, d.departments_name as departments_name, e.position as position, e.images as images FROM products p JOIN employees_products ep ON ep.product_id = p.product_id JOIN employee e ON e.employee_id = ep.employee_id JOIN user u ON  e.user_id = u.user_id JOIN department d ON u.department_id = d.department_id WHERE p.product_id = " + id).getResultList();
        if (res.size() > 0) {
            ProductsDTO p = new ProductsDTO();
            p.setProduct_id(Long.valueOf(res.get(0)[0].toString()));
            p.setProducts_code(Long.valueOf(res.get(0)[1].toString()));
            p.setProducts_name((String) res.get(0)[2]);
            p.setEnterprise((String) res.get(0)[3]);
            p.setStatus((Boolean) res.get(0)[4]);
            p.setContents((String) res.get(0)[5]);
            p.setTime_start((String) res.get(0)[6]);
            p.setTime_end((String) res.get(0)[7]);
            p.setComments((String) res.get(0)[8]);
            p.setFullname((String) res.get(0)[9]);
            p.setEmployee_id(Long.valueOf(res.get(0)[10].toString()));
            p.setEmployee_code((String) res.get(0)[11]);
            p.setPhone((String) res.get(0)[12]);
            p.setEmail((String) res.get(0)[13]);
            p.setDepartments_name((String) res.get(0)[14]);
            p.setPosition((String) res.get(0)[15]);
            p.setImages((String) res.get(0)[16]);
            return p;
        }
        return null;
    }
}
