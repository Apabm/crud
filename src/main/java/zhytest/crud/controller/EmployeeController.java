package zhytest.crud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import zhytest.crud.bean.Employee;
import zhytest.crud.bean.Msg;
import zhytest.crud.service.impl.EmployeeServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Zhy
 * @create 2021-11-22 14:40
 */

@Controller
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;




    /*使用Json*/
    @RequestMapping(value = "/emps/{pn}/{admin_department}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmps(@PathVariable("pn") Integer pn,@PathVariable("admin_department") Integer adminDepartment) {
        /*使用PageHelper*/
        PageHelper.startPage(pn, 5);

        List<Employee> emps = employeeService.getAll(adminDepartment);



        /*封装了详细分页信息，包括查询的出来的数据，以及需要连续显示的页数*/
        PageInfo employeePageInfo = new PageInfo(emps,5);
        Msg pageInfo = Msg.success().add("pageInfo", employeePageInfo);
        return pageInfo;
    }


    /*后端注册校验提醒*/
    @ResponseBody
    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public Msg checkUser(@RequestParam("empName") String empName) {
        String regName = "(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if (!(empName.matches(regName))) {
            return Msg.fail().add("va_msg", "用户名必须为3-16位英文或者2-5位中文");
        }
        boolean b = employeeService.checkUser(empName);

        if (b) {
            return Msg.success().add("va_msg", "用户名合法");
        } else {
            return Msg.fail().add("va_msg", "用户名已存在");
        }
    }

    /*后端注册校验提醒*/
    @ResponseBody
    @RequestMapping(value = "/checkUser/{id}", method = RequestMethod.POST)
    public Msg checkUser(@PathVariable Integer id) {
        if(!(employeeService.checkUserId(id))){
            return Msg.fail().add("va_msg_id","该员工Id已存在！");
        }else {
            return Msg.success().add("va_msg_id","该员工Id可用！");
        }
    }


    /*保存用户信息*/
    /*使用JSR，防止恶意修改前端*/
    /*BindingResult封装校验的结果*/
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {

        System.out.println(employee.getdId());
        if(!(employeeService.checkUserId(employee.getEmpId()))){
            return Msg.fail().add("va_msg_id","该员工Id已存在！aaa");
        }

        if (!(employeeService.checkUser(employee.getEmpName()))) {
            return Msg.fail().add("va_msg", "用户名已存在！(JSR)");
        }

        if (result.hasErrors()) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                hashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", hashMap);
        } else {
            employeeService.saveEmp(employee);
            Msg success = Msg.success();
            return success;
        }

    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable Integer id) {
        Employee employee = employeeService.getEmpById(id);
        System.out.println(employee);
        return Msg.success().add("employee", employee);
    }

    @RequestMapping(value = "/emp/{id}/{dId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id,@PathVariable("dId") Integer dId) {
        Employee employee = employeeService.getEmp(id,dId);
        return Msg.success().add("employee", employee);
    }


    /*更新员工信息*/
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee) {
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /*删除员工信息*/
    /*单个删除，也可以批量删除*/
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable String ids) {
        if (!(ids.contains("-"))) {
            employeeService.deleteEmp(Integer.parseInt(ids));
            return Msg.success();
        }else {
            String[] split = ids.split("-");
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(String i:split) {
                list.add(Integer.parseInt(i));
            }
            employeeService.deleteBatch(list);
            return Msg.success();
        }
    }


    //模糊查询
    @ResponseBody
    @GetMapping("/selectLike/{empName}/{dId}")
    public Msg selectByEmpNameAndDIdWithFuzzyQuery(@PathVariable("empName")String empName,@PathVariable("dId")Integer dId){
        List<Employee> employees = employeeService.selectByEmpNameAndDIdWithFuzzyQuery(empName, dId);
        return Msg.success().add("emps",employees);
    }



}
