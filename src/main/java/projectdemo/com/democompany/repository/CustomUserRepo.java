package projectdemo.com.democompany.repository;

import projectdemo.com.democompany.modelDTO.UserDTO;

import java.util.List;

public interface CustomUserRepo {
    public List<UserDTO> findAccount();
}
