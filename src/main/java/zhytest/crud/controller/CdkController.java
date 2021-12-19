package zhytest.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zhytest.crud.bean.Cdk;
import zhytest.crud.bean.Msg;
import zhytest.crud.service.CdkService;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-12-12 22:25
 */
@Controller
public class CdkController {

    @Autowired
    CdkService cdkService;

    @ResponseBody
    @GetMapping("/getCdks")
    public Msg getAllCdks(){
        List<Cdk> allCdks = cdkService.getAllCdks();

        return Msg.success().add("cdks",allCdks);
    }

    @PostMapping("/addCdk/{cdkNum}")
    @ResponseBody
    public Msg addCdk(@PathVariable String cdkNum){
        cdkService.addCdk(cdkNum);
        return Msg.success();
    }

    @DeleteMapping("/cdk/{cdk}")
    @ResponseBody
    public Msg deleteCdk(@PathVariable("cdk") String cdk){
        boolean b = cdkService.deleteCdkNum(cdk);
        if(b){
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }
}
