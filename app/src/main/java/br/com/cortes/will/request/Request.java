package br.com.cortes.will.request;

import static br.com.cortes.will.request.Authentication.PUBLIC_KEY;

public class Request {
    private String name;
    private int limit;
    private int offset;
    private Authentication auth;

    public Request() {
        auth = new Authentication();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getHash() {
        return auth.getmHash();
    }

    public String getTs() {
        return String.valueOf(auth.getmTs());
    }

    public String getPublicKey() {
        return PUBLIC_KEY;
    }
}
