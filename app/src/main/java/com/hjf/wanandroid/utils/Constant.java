package com.hjf.wanandroid.utils;

/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public class Constant {

    private Constant() {
    }

    /**
     * errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
     * errorCode = -1001 代表登录失效，需要重新登录。
     */
    public static final int ERROR_CODE_0 = 0;
    public static final int ERROR_CODE_1001 = -1001;

    public static final String RESPONSE_EMPTY = "暂无数据";
}
