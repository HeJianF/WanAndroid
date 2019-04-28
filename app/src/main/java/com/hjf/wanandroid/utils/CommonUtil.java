package com.hjf.wanandroid.utils;

import java.util.List;

/**
 * @author heJianfeng
 * @date 2019-04-28
 */
public class CommonUtil {

    private CommonUtil() {
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean noEmpty(List list) {
        return !isEmpty(list);
    }
}
