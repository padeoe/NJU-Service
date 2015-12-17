import com.padeoe.nicservice.njuwlan.object.portal.ReturnData;
import com.padeoe.nicservice.njuwlan.service.LoginService;
import com.padeoe.nicservice.njuwlan.service.OfflineQueryService;
import com.padeoe.nicservice.njuwlan.service.OnlineQueryService;

import java.io.IOException;

/**
 * Created by padeoe on 2015/9/16.
 */
public class Start {
    private static final String username = "***";
    private static final String password = "***";

    public static void main(String args[]) throws IOException, InterruptedException {

        String resultOnline = "";
        String resultAcct = "";
        String resultAuthlog = "";
        String resultBills = "";
        String resultRecharge = "";
        String resultBasic = "";
        String resultStandardOnline[] = new String[]{resultOnline, resultAcct, resultAuthlog, resultBills, resultRecharge, resultBasic};

        //开始测试OnlineDetailQuery
        OnlineQueryService onlineQueryService = OnlineQueryService.getInstance();
        onlineQueryService.setSettingsPortalIP("219.219.114.15");
        String result1 = onlineQueryService.getOnline(1, 5);
        String result2 = onlineQueryService.getAcct(1, 5);
        String result3 = onlineQueryService.getAuthLog(1, 5);
        String result4 = onlineQueryService.getBills(1, 5);
        String result5 = onlineQueryService.getRecharge(1, 5);
        String result6 = onlineQueryService.getBasicInfo();
        String resultGetByOnline[] = new String[]{result1, result2, result3, result4, result5, result6};
        for (int i = 0; i < resultGetByOnline.length; i++) {
            String result = resultGetByOnline[i];
            if (OnlineQueryService.isQuerySuccess(result, i)) {
                System.out.println("第" + (i + 1) + "项查询成功，返回结果：");
                System.out.println(result);
                if (result.equals(resultStandardOnline[i])) {
                    System.out.println("测试通过");
                } else {
                    System.out.println("测试失败，标准结果是:\n" + resultStandardOnline[i]);
                }
            } else {
                System.out.println("第" + (i + 1) + "项查询失败");
            }
        }
        String Online = "";
        String Acct = "";
        String Authlog = "";
        String Bills = "";
        String Recharge = "";
        String resultStandardOffline[] = new String[]{Online, Acct, Authlog, Bills, Recharge};

        //下面一行查询参数需要填写用户名密码
        OfflineQueryService offlineQueryService = OfflineQueryService.getInstance(username, password);
        offlineQueryService.setSettingsBrasIP("219.219.114.254");
        String result7 = offlineQueryService.getOnline(1, 20);
        String result8 = offlineQueryService.getAcct(1, 20);
        String result9 = offlineQueryService.getAuthLog(1, 20);
        String result10 = offlineQueryService.getBills(1, 20);
        String result11 = offlineQueryService.getRecharge(1, 20);

        String b[] = new String[]{result7, result8, result9, result10, result11};
        for (int i = 0; i < b.length; i++) {
            String result = b[i];
            if (OnlineQueryService.isQuerySuccess(result, i)) {
                System.out.println("第" + (i + 1) + "项查询成功，返回结果：");
                System.out.println(result);
                if (result.equals(resultStandardOffline[i])) {
                    System.out.println("测试通过");
                } else {
                    System.out.println("测试失败，标准结果是:\n" + resultStandardOffline[i]);
                }
            } else {
                System.out.println("第" + (i + 1) + "项查询失败，返回结果：" + result);
            }
        }
        LoginService.getInstance().setSettingsPortalIP("219.219.114.15");
        System.out.println((LoginService.getInstance().isPortalOnline()) ? "在线" : "离线");
        String result=LoginService.getInstance().connect(username, password);
        ReturnData returnData=ReturnData.getFromJson(result);
        System.out.println(result);
        System.out.println(returnData.getReply_code());
    }


}
