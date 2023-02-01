package projectdemo.com.democompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projectdemo.com.democompany.config.CustomUser;
import projectdemo.com.democompany.entity.UserEntity;
import projectdemo.com.democompany.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String em) throws UsernameNotFoundException {

        try {
            UserEntity user = repo.findByEmail(em);

            if (user == null) {
                throw new UsernameNotFoundException("No User");
            } else {
                return new CustomUser(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
