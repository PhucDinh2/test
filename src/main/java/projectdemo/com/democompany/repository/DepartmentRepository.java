package projectdemo.com.democompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projectdemo.com.democompany.entity.DepartmentEntity;
import projectdemo.com.democompany.entity.UserEntity;
import projectdemo.com.democompany.modelDTO.UserDTO;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
