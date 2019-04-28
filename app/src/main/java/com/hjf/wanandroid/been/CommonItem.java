package com.hjf.wanandroid.been;

/**
 * @author heJianfeng
 * @date 2018/12/14
 */
public class CommonItem {

    /**
     * Adapter中ViewHolder的type
     * getItemViewType
     */
    public int type;

    /**
     * 对应type的数据object
     */
    public Object object;

    public CommonItem(int type, Object object) {
        this.type = type;
        this.object = object;
    }
}
