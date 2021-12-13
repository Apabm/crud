package zhytest.crud.service;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.service.IService;
import zhytest.crud.bean.Employee;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-12-06 21:40
 */


public interface EmployeeService extends IService<Employee> {
    List<Employee> selectByEmpNameAndDIdWithFuzzyQuery(String empName,Integer dId);
}
