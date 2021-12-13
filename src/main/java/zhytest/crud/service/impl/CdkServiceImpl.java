package zhytest.crud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhytest.crud.bean.Cdk;
import zhytest.crud.mapper.CdkMapper;
import zhytest.crud.service.CdkService;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-12-07 18:40
 */
@Service
public class CdkServiceImpl implements CdkService {

    @Autowired
    CdkMapper cdkMapper;

    @Override
    public Integer countCdkByCdkNum(String cdkNum) {
        return cdkMapper.countCdkByCdkNum(cdkNum);
    }

    @Override
    public boolean deleteCdkNum(String cdkNum) {
        if(cdkMapper.countCdkByCdkNum(cdkNum)==1){
            cdkMapper.deleteCdkNum(cdkNum);
            return true;
        }
        return false;
    }

    @Override
    public List<Cdk> getAllCdks() {
        return cdkMapper.getAllCdks();
    }

    @Override
    public void addCdk(String cdkNum) {
        cdkMapper.addCdk(cdkNum);
    }


}
