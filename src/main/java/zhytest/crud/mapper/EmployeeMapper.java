package zhytest.crud.mapper;


import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zhytest.crud.bean.Employee;
import zhytest.crud.bean.EmployeeExample;

import java.util.List;


@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    List<Employee> selectByDepartmentWithDept(Integer adminDepartment);

    List<Employee> getAll();

    Employee selectByPrimaryKey(Integer empId);

    Employee selectByPrimaryKeyWithDept(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    Employee selectByIdAndDId(@Param("id") Integer id,@Param("dId")Integer dId);

    List<Employee> selectByEmpNameAndDIdWithFuzzyQuery(@Param("empName") String empName,@Param("dId")Integer dId);
}