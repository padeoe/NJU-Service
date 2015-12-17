import com.padeoe.nicservice.njuwlan.object.bras.row.AcctRowBras;
import com.padeoe.nicservice.njuwlan.object.portal.list.Acct;
import com.padeoe.nicservice.njuwlan.object.portal.row.AcctRow;
import com.padeoe.nicservice.njuwlan.service.OnlineQueryService;

/**
 * Created by padeoe on 2015/12/17.
 */
public class CalculateData {
    /**
     * 计算本月的下行流量总量
     * 需要在已登录的情况下运行
     * @param args
     */
    public static void main(String args[]){
        OnlineQueryService onlineQueryService=OnlineQueryService.getInstance();
        String testResult=onlineQueryService.getAcct(1,1000);
        System.out.println(testResult);
        Acct test=Acct.getFromJson(testResult);
        double total=Integer.valueOf(test.getTotal());
        int rows=test.getAcctRows().length;
        int page=(int)Math.ceil(total/rows);
        long data=0;
        System.out.println(total+","+rows+","+page);
        for(int i=1;i<=page;i++){
            Acct acct=Acct.getFromJson(onlineQueryService.getAcct(i,1000));
            AcctRow[] acctRow=acct.getAcctRows();
            for(int j=0;j<acctRow.length;j++){
                System.out.println(acctRow[j].getAcctoutputoctets_ipv4());
                data+=Long.parseLong(acctRow[j].getAcctoutputoctets_ipv4());
            }
        }
        System.out.println("总流量="+data/1024/1024+"M");
    }
}
