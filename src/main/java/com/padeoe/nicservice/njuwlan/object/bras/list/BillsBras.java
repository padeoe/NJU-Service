package com.padeoe.nicservice.njuwlan.object.bras.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.padeoe.nicservice.njuwlan.object.bras.Base;
import com.padeoe.nicservice.njuwlan.object.bras.row.BillsRowBras;

/**
 * 该类表示<a href="http://bras.nju.edu.cn">南京大学网络认证计费系统自助平台</a>中查询账单信息获得的列表
 * 具体包含一个包含{@link BillsRowBras}对象的数组以及账单列表的总页数
 *
 * @author padeoe
 * Date: 2015/9/23
 */
public class BillsBras extends Base {
    @JSONField(name = "rows")
    private BillsRowBras[] billsRowBrases;
    @JSONField(name = "page")
    protected String pageNum;

    public BillsBras() {
    }

    public static BillsBras getFromJson(String jsonobject) {
        try {
            return JSON.parseObject(jsonobject, BillsBras.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public BillsRowBras[] getBillsRowBrases() {
        return billsRowBrases;
    }

    public void setBillsRowBrases(BillsRowBras[] billsRowBrases) {
        this.billsRowBrases = billsRowBrases;
    }

    @Override
    public String toString() {
        return "BillsBras:[\n" +
                "max_page = " + max_page + ",\n" +
                "reply_code = " + reply_code + ",\n" +
                "reply_msg = " + reply_msg + ",\n" +
                "pageNum = " + pageNum + ",\n" +
                "request_time = " + request_time + ",\n" +
                "request_url = " + request_url + ",\n" +
                billsRowBrases.toString() + ",\n]\n";
    }
}


