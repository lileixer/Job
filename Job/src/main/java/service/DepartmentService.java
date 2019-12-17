package service;

import domain.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    List<Department> findPart();
}
