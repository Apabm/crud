package zhytest.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import zhytest.crud.bean.Department;
import zhytest.crud.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhytest.crud.service.DepartmentService;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-11-23 14:03
 */

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper,Department> implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


    public List<Department> getDepts() {
        List<Department> list = departmentMapper.selectByExample(null);
        return list;
    }

    public Department getDeptById(Integer id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }

    @Override
    public int addDept(Department department) {
        int insert = departmentMapper.insert(department);
        return insert;
    }

    public long getDeptNum(){
        return departmentMapper.countByExample(null);
    }

    @Override
    public int deleteDept(Integer deptId) {
        return  departmentMapper.deleteByPrimaryKey(deptId);
    }


}
