import com.padeoe.nicservice.njuwlan.object.bras.list.AcctBras;
import com.padeoe.nicservice.njuwlan.object.bras.row.AcctRowBras;
import com.padeoe.nicservice.njuwlan.service.OfflineQueryService;

/**
 * Created by padeoe on 2015/12/14.
 */
public class CalculateAccount {
    private static final String username = "***";
    private static final String password = "***";

    public static void main(String args[]) {
        //计算历史详单收费实收和应收
        OfflineQueryService offlineQueryService = OfflineQueryService.getInstance(username, password);
        String test = offlineQueryService.getAcct(11, 1, 20, true);
        System.out.println(test);
        AcctBras testAcctBras = AcctBras.getFromJson(test);
        int page = Integer.valueOf(testAcctBras.getMax_page());
        System.out.println(testAcctBras.getPageNum()+"here");

        double account = 0;
        double time = 0;
        boolean islast = false;
        for (int i = 1; i <= page; i++) {
            String result = offlineQueryService.getAcct(11, i, 20, true);
            System.out.println(result);
            AcctBras acctBras = AcctBras.getFromJson(result);
            AcctRowBras[] acctRowBras = acctBras.getAcctRowBrases();
            System.out.println("获取记录条数" + acctRowBras.length);
            for (int k = 0; k < acctRowBras.length - 1; k++) {
                double accountTmp = Integer.valueOf(acctRowBras[k].getAmount_ipv4());
                if (accountTmp > 0) {
                    account += accountTmp;
                    time += Integer.valueOf(acctRowBras[k].getAcctsessiontime());
                }
            }
        }
        System.out.println("网费总收费" + account/1000);
        System.out.println("网费应收费" + (time / 60 / 60 * 0.20));
    }
}
