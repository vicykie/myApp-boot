package org.vicykie.myapp.vo;


import org.vicykie.myapp.enums.ResponseStatus;

/**
 * Created by vicykie on 2016/5/5.
 */
public class ResponseVO<T> {
    private ResponseStatus code;
    private String rspMsg;
    private T data;

    public ResponseStatus getCode() {
        return code;
    }

    public void setCode(ResponseStatus code) {
        this.code = code;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseVO(ResponseStatus code, String rspMsg, T data) {
        this.code = code;
        this.rspMsg = rspMsg;
        this.data = data;
    }

    public ResponseVO(ResponseStatus code, String rspMsg) {
        this.code = code;
        this.rspMsg = rspMsg;
    }

    public ResponseVO(ResponseStatus code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseVO<T> getDataSuccess(T data, String rspMsg) {
        return new ResponseVO(ResponseStatus.SUC_GET_DATA, rspMsg, data);
    }

    public static ResponseVO getDataError(ResponseStatus code, String rspMsg) {
        return new ResponseVO(code, rspMsg);
    }

    public static ResponseVO updateSuccess(String rspMsg) {
        return new ResponseVO(ResponseStatus.SUC_UPDATE, rspMsg);
    }

    public static ResponseVO updateError(String rspMsg, ResponseStatus code) {
        return new ResponseVO(code, rspMsg);
    }

    public static ResponseVO delSuccess(String rspMsg) {
        return new ResponseVO(ResponseStatus.SUC_DELETE, rspMsg);
    }

    public static ResponseVO delError(String rspMsg, ResponseStatus code) {
        return new ResponseVO(code, rspMsg);
    }

    public static <T> ResponseVO<T> loginSuccess(T successUrl) {
        return new ResponseVO(ResponseStatus.SUC_LOGIN, "登录成功", successUrl);
    }

    public static <T> ResponseVO<T> loginError(String rspMsg) {
        return new ResponseVO(ResponseStatus.ERR_LOGIN, rspMsg);
    }

    public static <T> ResponseVO<T> logoutSuccess(T successUrl) {
        return new ResponseVO(ResponseStatus.SUC_LOGOUT, "退出成功", successUrl);
    }


}
