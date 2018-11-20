package org.mountcloud.springmvc.framework;

/**
 * 返回的总结构
 * @author zhanghaishan
 * @version V1.0
 * date 2017/12/20.
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RestfulResult")
public class RestfulResult<T> {

    /**
     * 状态
     */
    private int status = 0;
    /**
     * 数据
     */
    private T data;
    /**
     * 消息
     */
    private String message;

    public RestfulResult() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public static enum ResultStatus {
        SUCCESS(0),
        FAILE(-1);

        private int value;

        private ResultStatus(int value) {
            this.setValue(value);
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
