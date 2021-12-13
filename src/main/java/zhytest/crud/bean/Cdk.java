package zhytest.crud.bean;

/**
 * @author Zhy
 * @create 2021-12-07 18:33
 */
public class Cdk {
    private String cdkNum;

    public String getCdkNum() {
        return cdkNum;
    }

    public void setCdkNum(String ckdNum) {
        this.cdkNum = ckdNum;
    }

    @Override
    public String toString() {
        return "Cdk{" +
                "cdkNum='" + cdkNum + '\'' +
                '}';
    }
}
