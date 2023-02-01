package projectdemo.com.democompany.repository;

import projectdemo.com.democompany.entity.ProductsEntity;
import projectdemo.com.democompany.modelDTO.ProductsDTO;

import java.util.List;

public interface CustomProductsRepo {

    public List<ProductsDTO> findProducts();

    public ProductsDTO findByIdProducts(long id);
}
