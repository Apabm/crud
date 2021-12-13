package zhytest.crud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zhytest.crud.bean.Cdk;

import java.util.List;

/**
 * @author Zhy
 * @create 2021-12-07 18:35
 */
@Mapper
public interface CdkMapper {
    Integer countCdkByCdkNum(@Param("cdk_num") String cdkNum);
    void deleteCdkNum(@Param("cdk_num")String cdkNum);
    List<Cdk> getAllCdks();
    void addCdk(String cdkNum);
}
