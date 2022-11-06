package com.autumn.wallet.form.message;


import com.autumn.wallet.form.PageForm;

public class QueryUserNewsParams extends PageForm {
    private Integer userid;

    private Integer readType;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getReadType() {
        return readType;
    }

    public void setReadType(Integer readType) {
        this.readType = readType;
    }
}
