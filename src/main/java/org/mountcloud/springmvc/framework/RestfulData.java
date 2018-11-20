package org.mountcloud.springmvc.framework;

/**
 * TODO: 返回的data结构
 * com.ugirls.statisticalservice.common
 * 2018/11/12.
 *
 * @author zhanghaishan
 * @version V1.0
 */
public class RestfulData<T> {
    /**
     * 数据
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
