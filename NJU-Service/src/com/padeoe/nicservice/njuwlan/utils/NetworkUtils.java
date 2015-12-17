package com.padeoe.nicservice.njuwlan.utils;

import com.padeoe.utils.network.MyHttpRequest;

import java.net.InetAddress;
import java.util.concurrent.*;

/**
 * Created by padeoe on 2015/9/23.
 */
public class NetworkUtils {
    /**
     * 当DNS解析p.nju.edu.cn的IP失败时使用的IP
     */
    private static String defaultPortalIP = "210.28.129.9";
    /**
     * 当DNS解析bras.nju.edu.cn的IP失败时使用的IP
     */
    private static String defaultBrasIP = "219.219.114.254";

    /**
     * Post请求(仅适用于p.nju.edu.cn及bras.nju.edu.cn)
     *
     * @param postData 发送的数据
     * @param URL 服务器地址
     *@param timeout 超时时间
     * @returnPost
     */
    public static String connectAndPost(String postData, String URL, int timeout) {
        return MyHttpRequest.post(postData, URL, null, "UTF-8", "UTF-8", timeout);
    }

    /**
     * Post请求,含cookie(仅适用于p.nju.edu.cn及bras.nju.edu.cn)
     * @param postData 发送的数据
     * @param cookie cookie
     * @param URL 服务器地址
     * @param timeout 超时时间
     * @return
     */
    public static String postWithCookie(String postData, String cookie, String URL, int timeout) {
        return MyHttpRequest.postWithCookie(postData, URL, null, cookie, "UTF-8", "UTF-8", timeout);
    }

    /**
     * Post请求并获得cookie(仅适用于p.nju.edu.cn及bras.nju.edu.cn)
     * @param postData 发送的数据
     * @param URL 服务器地址
     * @param timeout 超时时间
     * @return
     */
    public static String[] postAndGetCookie(String postData, String URL, int timeout) {
        return MyHttpRequest.postAndGetCookie(postData, URL, null, "UTF-8", "UTF-8", timeout);
    }

    /**
     * 获取当前p.nju.edu.cn的IP
     * @return DNS查询到的IP,若DNS查询失败则返回默认IP
     */
    public static String getCurrentPortalIP() {
        try {
            return DNS("p.nju.edu.cn", 200);
        } catch (Exception e) {
            return getDefaultPortalIP();
        }

    }

    /**
     * 获取当前bras.nju.edu.cn的IP
     * @return DNS查询到的IP,若DNS查询失败则返回默认IP
     */
    public static String getCurrentBrasIP() {
        try {
            return DNS("bras.nju.edu.cn", 200);
        } catch (Exception e) {
            return getDefaultPortalIP();
        }
    }

    /**
     * 解析域名IP
     *
     * @param URL     域名
     * @param timeout 超时时间
     * @return IP
     */
    public static String DNS(final String URL, int timeout) throws InterruptedException, ExecutionException, TimeoutException {
        class Task implements Callable<String> {
            @Override
            public String call() throws java.net.UnknownHostException {
                InetAddress[] inetAddresses;
                inetAddresses = InetAddress.getAllByName(URL);
                return inetAddresses[0].getHostAddress();
            }
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task());
        try {
            System.out.println("开始解析DNS");
            String result = future.get(timeout, TimeUnit.MILLISECONDS);
            executor.shutdownNow();
            return result;
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            future.cancel(true);
            System.out.println("解析失败");
            executor.shutdownNow();
            throw e;
        }
    }

    /**
     * 查看DNS解析p.nju.edu.cn失败时使用的默认IP
     *
     * @return
     */
    public static String getDefaultPortalIP() {
        return defaultPortalIP;
    }

    /**
     * 设置DNS解析p.nju.edu.cn失败时使用的默认IP
     *
     * @param defaultPortalIP 指定的IP
     */
    public static void setDefaultPortalIP(String defaultPortalIP) {
        NetworkUtils.defaultPortalIP = defaultPortalIP;
    }

    /**
     * 查看DNS解析bras.nju.edu.cn失败时使用的默认IP
     *
     * @return
     */
    public static String getDefaultBrasIP() {
        return defaultBrasIP;
    }

    /**
     * 设置DNS解析bras.nju.edu.cn失败时使用的默认IP
     *
     * @param defaultBrasIP 指定的IP
     */
    public static void setDefaultBrasIP(String defaultBrasIP) {
        NetworkUtils.defaultBrasIP = defaultBrasIP;
    }
}
