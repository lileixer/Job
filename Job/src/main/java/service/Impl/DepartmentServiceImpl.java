package service.Impl;

import dao.Impl.DepartmentDaoImpl;
import domain.Department;
import service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    @Override
    public List<Department> findAll() {
        return new DepartmentDaoImpl().findAll();
    }

    @Override
    public List<Department> findPart() { return new DepartmentDaoImpl().findPart(); }
}
