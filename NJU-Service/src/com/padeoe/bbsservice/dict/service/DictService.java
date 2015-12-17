package com.padeoe.bbsservice.dict.service;

import com.padeoe.utils.network.MyHttpRequest;

/**
 * Created by padeoe on 2015/12/13.
 */
public class DictService {
    /**
     * 查询英语单词释义
     * @param word 英语单词
     * @return 单词解释的html页面
     */
    public static String lookUp(String word) {
        return MyHttpRequest.post("word="+word,"http://bbs.nju.edu.cn/bbsdict?type=1",null,"UTF-8","GBK",500);
    }
}
