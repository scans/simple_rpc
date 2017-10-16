package cn.coolcc;

/**
 * 响应
 * Created by chencheng on 17/9/20.
 */
public class Response {
    private Integer requestId;
    private Object result;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
