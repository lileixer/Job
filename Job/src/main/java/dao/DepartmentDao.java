package dao;

import domain.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll();
    List<Department> findPart();
}
