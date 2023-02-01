package projectdemo.com.democompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectdemo.com.democompany.entity.ProductsEntity;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long>, CustomProductsRepo {

}
