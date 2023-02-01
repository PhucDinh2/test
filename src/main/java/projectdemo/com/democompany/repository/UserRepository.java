package projectdemo.com.democompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projectdemo.com.democompany.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepo {
    public UserEntity findByEmail(String em);
}
