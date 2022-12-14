package com.autumn.domain.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class Tmicroaddrtokenappend {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tmicroaddrtokenappend.matiid
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Integer matiid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tmicroaddrtokenappend.tokenid
     *
     * @mbg.generated
     */
    private Integer tokenid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tmicroaddrtokenappend.accountid
     *
     * @mbg.generated
     */
    private Integer accountid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tmicroaddrtokenappend.maiid
     *
     * @mbg.generated
     */
    private Integer maiid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tmicroaddrtokenappend.mtotalaccquantity
     *
     * @mbg.generated
     */
    private BigDecimal mtotalaccquantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tmicroaddrtokenappend.mtokensymbol
     *
     * @mbg.generated
     */
    private String mtokensymbol;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tmicroaddrtokenappend.matiid
     *
     * @return the value of tmicroaddrtokenappend.matiid
     *
     * @mbg.generated
     */
    private BigDecimal museaccquantity;

    public BigDecimal getMuseaccquantity() {
        return museaccquantity;
    }

    public void setMuseaccquantity(BigDecimal museaccquantity) {
        this.museaccquantity = museaccquantity;
    }

    public Integer getMatiid() {
        return matiid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tmicroaddrtokenappend.matiid
     *
     * @param matiid the value for tmicroaddrtokenappend.matiid
     *
     * @mbg.generated
     */
    public void setMatiid(Integer matiid) {
        this.matiid = matiid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tmicroaddrtokenappend.tokenid
     *
     * @return the value of tmicroaddrtokenappend.tokenid
     *
     * @mbg.generated
     */
    public Integer getTokenid() {
        return tokenid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tmicroaddrtokenappend.tokenid
     *
     * @param tokenid the value for tmicroaddrtokenappend.tokenid
     *
     * @mbg.generated
     */
    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tmicroaddrtokenappend.accountid
     *
     * @return the value of tmicroaddrtokenappend.accountid
     *
     * @mbg.generated
     */
    public Integer getAccountid() {
        return accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tmicroaddrtokenappend.accountid
     *
     * @param accountid the value for tmicroaddrtokenappend.accountid
     *
     * @mbg.generated
     */
    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tmicroaddrtokenappend.maiid
     *
     * @return the value of tmicroaddrtokenappend.maiid
     *
     * @mbg.generated
     */
    public Integer getMaiid() {
        return maiid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tmicroaddrtokenappend.maiid
     *
     * @param maiid the value for tmicroaddrtokenappend.maiid
     *
     * @mbg.generated
     */
    public void setMaiid(Integer maiid) {
        this.maiid = maiid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tmicroaddrtokenappend.mtotalaccquantity
     *
     * @return the value of tmicroaddrtokenappend.mtotalaccquantity
     *
     * @mbg.generated
     */
    public BigDecimal getMtotalaccquantity() {
        return mtotalaccquantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tmicroaddrtokenappend.mtotalaccquantity
     *
     * @param mtotalaccquantity the value for tmicroaddrtokenappend.mtotalaccquantity
     *
     * @mbg.generated
     */
    public void setMtotalaccquantity(BigDecimal mtotalaccquantity) {
        this.mtotalaccquantity = mtotalaccquantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tmicroaddrtokenappend.mtokensymbol
     *
     * @return the value of tmicroaddrtokenappend.mtokensymbol
     *
     * @mbg.generated
     */
    public String getMtokensymbol() {
        return mtokensymbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tmicroaddrtokenappend.mtokensymbol
     *
     * @param mtokensymbol the value for tmicroaddrtokenappend.mtokensymbol
     *
     * @mbg.generated
     */
    public void setMtokensymbol(String mtokensymbol) {
        this.mtokensymbol = mtokensymbol == null ? null : mtokensymbol.trim();
    }
}