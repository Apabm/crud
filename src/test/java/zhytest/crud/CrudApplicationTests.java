package zhytest.crud;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhytest.crud.bean.Admin;
import zhytest.crud.bean.Cdk;
import zhytest.crud.bean.Department;
import zhytest.crud.bean.Employee;
import zhytest.crud.mapper.AdminMapper;
import zhytest.crud.mapper.DepartmentMapper;
import zhytest.crud.mapper.EmployeeMapper;
import zhytest.crud.mapper.RootMapper;
import zhytest.crud.service.AdminService;
import zhytest.crud.service.CdkService;
import zhytest.crud.service.DepartmentService;
import zhytest.crud.service.RootService;
import zhytest.crud.service.impl.AdminServiceImpl;
import zhytest.crud.service.impl.DepartmentServiceImpl;

import java.util.List;

@SpringBootTest
class CrudApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;
    
    @Autowired
    AdminService adminService;

    @Autowired
    CdkService cdkService;

    @Autowired
    RootMapper rootMapper;

    @Autowired
    RootService rootService;

    @Autowired
    DepartmentMapper departmentMapper;


    @Test
    void deptTest(){
        long l = departmentMapper.countByExample(null);
        System.out.println("===================================");
        System.out.println(l);
        departmentMapper.insert(new Department(4,"条眼部"));
    }

    @Test
    void contextLoads() {
        Admin root = adminService.selectByLoginId("root");
      
    }


    @Test
    void testEmp(){
        System.out.println(employeeMapper.selectByEmpNameAndDIdWithFuzzyQuery("J",2));
    }

    @DisplayName("测试CDK")
    @Test
    void testCdk(){
        cdkService.addCdk("asdp");

    }

    @DisplayName("测试Root")
    @Test
    void testRoot(){
        System.out.println(rootService.verifyIdAndPassword("root","root"));
        Assertions.assertEquals(1,rootMapper.verifyIdAndPassword("root","root"));
    }

    @Test
    void testCdks(){
        List<Cdk> allCdks = cdkService.getAllCdks();
        System.out.println(allCdks);

    }

}
