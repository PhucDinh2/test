package projectdemo.com.democompany.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projectdemo.com.democompany.entity.EmployeeEntity;
import projectdemo.com.democompany.entity.ProductsEntity;
import projectdemo.com.democompany.modelDTO.EmployeeDTO;
import projectdemo.com.democompany.modelDTO.ProductsDTO;
import projectdemo.com.democompany.repository.EmployeeRepository;
import projectdemo.com.democompany.repository.ProductsRepository;
import projectdemo.com.democompany.request.ProductRequest;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class ProductController {

    private static final Logger LOGGER= LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductsRepository productsRepo;

    @Autowired
    private EmployeeRepository employeeRepo;


    @GetMapping("/products")
    public String loadProducts(Model m) {
        List<ProductsDTO> list = productsRepo.findProducts();
        m.addAttribute("all_product", list);
        return "management_products";
    }

    @GetMapping("/detailproduct/{id}")
    public String detailProduct(@PathVariable(value = "id") long id, Model m) {
        Optional<ProductsEntity> products = productsRepo.findById(id);
        ProductsEntity list = products.get();
        m.addAttribute("all_product", list);
        return "details_project";
    }

    @GetMapping("/createproducts")
    public String createProducts(Model m) {
        List<EmployeeDTO> list = employeeRepo.findEmployee();
        m.addAttribute("all_employee", list);
        return "create_products";
    }

    @GetMapping("/editproduct/{id}")
    public String editProducts(@PathVariable(value = "id") long id, Model m) {
        List<EmployeeDTO> list = employeeRepo.findEmployee();
        LOGGER.info("Get list product: " +list);
        m.addAttribute("all_employee", list);
        Optional<ProductsEntity> products = productsRepo.findById(id);
        ProductsEntity product = products.get();
        m.addAttribute("all_products", product);
        return "update_products";
    }

    @PostMapping("/saveproducts")
    public String saveProducts(@ModelAttribute ProductRequest product, HttpSession session) {
        LOGGER.info("ProductRequest save product: " +product);
        List<EmployeeEntity> employeeEntities = employeeRepo.findAllById(product.getList_employees());
//        log.info("listEmployee: "+employeeEntities.size());
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setProducts_code(product.getProducts_code());
        productsEntity.setProducts_name(product.getProducts_name());
        productsEntity.setEnterprise(product.getEnterprise());
        productsEntity.setStatus(product.getStatus());
        productsEntity.setEmployees(employeeEntities);
        productsEntity.setContents(product.getContents());
        productsEntity.setTime_start(product.getTime_start());
        productsEntity.setTime_end(product.getTime_end());
        productsEntity.setComments(product.getComments());
        productsRepo.save(productsEntity);
        session.setAttribute("msg", "Products Added Successfully!!!");
        return "redirect:/user/products";
    }

    @PostMapping("updateproduct/{id}")
    public String updateProducts(@PathVariable(value = "id") long id, @ModelAttribute ProductRequest products, HttpSession session) {
        LOGGER.info("ProductRequest update product: " +products);
        List<EmployeeEntity> employeeEntities = employeeRepo.findAllById(products.getList_employees());
        Optional<ProductsEntity> productsEnti = productsRepo.findById(id);
        ProductsEntity product = productsEnti.get();
        product.setProduct_id(id);
        product.setProducts_code(products.getProducts_code());
        product.setProducts_name(products.getProducts_name());
        product.setEnterprise(products.getEnterprise());
        product.setStatus(products.getStatus());
        product.setEmployees(employeeEntities);
        product.setContents(products.getContents());
        product.setTime_start(products.getTime_start());
        product.setTime_end(products.getTime_end());
        product.setComments(products.getComments());
        productsRepo.save(product);
        session.setAttribute("msg", "Products Update Successfully!!!");
        return "redirect:/user/products";
    }

    @GetMapping("deleteproduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id, HttpSession session) {
        productsRepo.deleteById(id);
        session.setAttribute("msg", "Products Delete Successfully!!!");
        return "redirect:/user/products";
    }
}
