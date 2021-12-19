package zhytest.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhytest.crud.bean.Department;
import zhytest.crud.bean.Msg;
import zhytest.crud.service.impl.DepartmentServiceImpl;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-11-23 14:01
 */

@Controller
public class DepartmentController {

    @Autowired
    DepartmentServiceImpl departmentService;


    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list = departmentService.getDepts();
        Msg depts = Msg.success().add("depts", list);
        return depts;
    }

    @RequestMapping(value = "/depts/{dId}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getDeptById(@PathVariable Integer dId){
        Department dept = departmentService.getDeptById(dId);
        Msg msg = Msg.success().add("dept", dept);
        return msg;
    }

    @PostMapping("/depts/{deptName}")
    @ResponseBody
    public Msg addDept(@PathVariable("deptName")String deptName){
        long deptNum = departmentService.getDeptNum();
        int i = departmentService.addDept(new Department((int) (deptNum + 1), deptName));

        if(i==1){
            return Msg.success();
        }else {
            return Msg.fail();
        }

    }


    @DeleteMapping("/depts/{deptId}")
    @ResponseBody
    public Msg deleteDept(@PathVariable("deptId")Integer deptId){
        int num = departmentService.deleteDept(deptId);
        if(num == 1){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }





}
