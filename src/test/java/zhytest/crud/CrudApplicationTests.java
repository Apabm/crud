package zhytest.crud;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zhytest.crud.bean.Admin;
import zhytest.crud.bean.Cdk;
import zhytest.crud.bean.Employee;
import zhytest.crud.mapper.AdminMapper;
import zhytest.crud.mapper.EmployeeMapper;
import zhytest.crud.mapper.RootMapper;
import zhytest.crud.service.AdminService;
import zhytest.crud.service.CdkService;
import zhytest.crud.service.RootService;
import zhytest.crud.service.impl.AdminServiceImpl;

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

    @Test
    void contextLoads() {
        Admin root = adminService.selectByLoginId("root");
        System.out.println(adminService.getClass());
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
