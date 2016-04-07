import com.padeoe.nicservice.njuwlan.object.bras.list.AcctBras;
import com.padeoe.nicservice.njuwlan.object.bras.row.AcctRowBras;
import com.padeoe.nicservice.njuwlan.service.OfflineQueryService;
import com.padeoe.utils.LoginException;

/**
 * 该类用于计算NIC当前网络计费方法向上取整与目前广泛采用的向下取整计费方法的费用差别
 * @author padeoe
 * Date: 2015/12/14
 */
public class CalculateAccount {
    private static final String username = "***";
    private static final String password = "***";

    public static void main(String args[]) {
        //计算历史详单收费实收和应收
        try {
            OfflineQueryService offlineQueryService = new OfflineQueryService(username, password);
            String test = offlineQueryService.getAcct(11, 1, 20, true);
            System.out.println(test);
            AcctBras testAcctBras = AcctBras.getFromJson(test);
            if(testAcctBras!=null){
                int page = Integer.valueOf(testAcctBras.getMax_page());
                System.out.println(testAcctBras.getPageNum()+"here");
                double account = 0;
                double time = 0;
                boolean islast = false;
                for (int i = 1; i <= page; i++) {
                    String result = offlineQueryService.getAcct(11, i, 20, true);
                    System.out.println(result);
                    AcctBras acctBras = AcctBras.getFromJson(result);
                    if(acctBras!=null){
                        AcctRowBras[] acctRowBras = acctBras.getAcctRowBrases();
                        if(acctRowBras!=null){
                            System.out.println("获取记录条数" + acctRowBras.length);
                            for (int k = 0; k < acctRowBras.length - 1; k++) {
                                double accountTmp = Integer.valueOf(acctRowBras[k].getAmount_ipv4());
                                if (accountTmp > 0) {
                                    account += accountTmp;
                                    time += Integer.valueOf(acctRowBras[k].getAcctsessiontime());
                                }
                            }
                        }
                    }
                }
                System.out.println("网费总收费" + account/1000);
                System.out.println("网费应收费" + (time / 60 / 60 * 0.20));
            }
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
