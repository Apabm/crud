package zhytest.crud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import zhytest.crud.bean.Employee;
import zhytest.crud.bean.EmployeeExample;
import zhytest.crud.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhytest.crud.service.EmployeeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zhy
 * @create 2021-11-22 14:44
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService {

    @Resource
    EmployeeMapper employeeMapper;


    public List<Employee> getAll(Integer adminDepartment) {
        List<Employee> employees = employeeMapper.selectByDepartmentWithDept(adminDepartment);
        return employees;
    }


    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /*检查用户名是否重复*/
    public boolean checkUser(String empName) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(employeeExample);
        if(count == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUserId(Integer id){
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdEqualTo(id);
        long count = employeeMapper.countByExample(employeeExample);
        if(count == 0){
            return true;
        }else{
            return false;
        }
    }

    public Employee getEmp(Integer id,Integer dId) {
        Employee employee = employeeMapper.selectByIdAndDId(id,dId);
        return employee;
    }

    public Employee getEmpById(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> split) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(split);
        employeeMapper.deleteByExample(employeeExample);
    }

    @Override
    public List<Employee> selectByEmpNameAndDIdWithFuzzyQuery(String empName, Integer dId) {
        return employeeMapper.selectByEmpNameAndDIdWithFuzzyQuery(empName,dId);
    }
}
