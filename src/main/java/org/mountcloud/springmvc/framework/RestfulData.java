package org.mountcloud.springmvc.framework;

/**
 * 返回的data结构
 * @author zhanghaishan
 * @version V1.0
 * date 2018/11/12.
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
