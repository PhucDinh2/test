package projectdemo.com.democompany.repository;

import projectdemo.com.democompany.modelDTO.EmployeeDTO;

import java.util.List;

public interface CustomEmployeeRepo {
    public List<EmployeeDTO> findEmployee();

    public EmployeeDTO findByIdEmployee(long id);
}
