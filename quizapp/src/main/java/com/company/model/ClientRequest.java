package com.company.model;

public class ClientRequest {
    private int code;
    private String clientAction;

    public ClientRequest(int code, String clientAction) {
        this.code = code;
        this.clientAction = clientAction;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getClientAction() {
        return clientAction;
    }

    public void setClientAction(String clientAction) {
        this.clientAction = clientAction;
    }
}
