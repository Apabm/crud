package zhytest.crud.service;


import zhytest.crud.bean.Cdk;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-12-07 18:39
 */
public interface CdkService {
    Integer countCdkByCdkNum(String cdkNum);
    boolean deleteCdkNum(String cdkNum);
    List<Cdk> getAllCdks();
    void addCdk(String cdkNum);
}
