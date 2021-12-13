package zhytest.crud.bean;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author Zhy
 * @create 2021-12-07 1:50
 */

@TableName("tbl_admin")
public class Admin implements Serializable {
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$")
    private String loginId;

    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$")
    private String loginPassword;

    private Integer dId;


    public Admin() {
    }

    public Admin(@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$") String loginId, @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$") String loginPassword, Integer dId) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.dId = dId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "loginId='" + loginId + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", dId=" + dId +
                '}';
    }
}
