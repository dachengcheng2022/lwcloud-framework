package com.autumn.wallet.form.account;/**
 * Created by Administrator on 2018/7/25.
 */

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: xwallet
 * @description:MicroWithDrawRecordsForm
 * @author: Woody
 **/
public class MicroWithDrawRecordsForm {

    private Integer auditid;

    private Integer userid;

    private String username;

    private Integer coinid;

    private Integer tokenid;

    private Boolean txistoken;

    private String symbol;

    private String cnameen;

    private Boolean txisvaild;

    private String contractaddr;

    private Integer cointype;

    private Integer biztype;

    private String toaddress;

    private BigDecimal dquantity;

    private Date sumbittime;

    private String txhash;

    private Date audtime;

    private Integer audstatus;

    private BigDecimal dfee;

    private String dfeesymbol;

    private Integer idcardpass;

    private String realname;

    private String idcardno;

    public Integer getCointype() {
        return cointype;
    }

    public void setCointype(Integer cointype) {
        this.cointype = cointype;
    }

    public Integer getBiztype() {
        return biztype;
    }

    public void setBiztype(Integer biztype) {
        this.biztype = biztype;
    }

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public Integer getAudstatus() {
        return audstatus;
    }

    public void setAudstatus(Integer audstatus) {
        this.audstatus = audstatus;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCoinid() {
        return coinid;
    }

    public void setCoinid(Integer coinid) {
        this.coinid = coinid;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public Boolean getTxistoken() {
        return txistoken;
    }

    public void setTxistoken(Boolean txistoken) {
        this.txistoken = txistoken;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCnameen() {
        return cnameen;
    }

    public void setCnameen(String cnameen) {
        this.cnameen = cnameen;
    }

    public Boolean getTxisvaild() {
        return txisvaild;
    }

    public void setTxisvaild(Boolean txisvaild) {
        this.txisvaild = txisvaild;
    }

    public String getContractaddr() {
        return contractaddr;
    }

    public void setContractaddr(String contractaddr) {
        this.contractaddr = contractaddr;
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

    public BigDecimal getDquantity() {
        return dquantity;
    }

    public void setDquantity(BigDecimal dquantity) {
        this.dquantity = dquantity;
    }

    public Date getSumbittime() {
        return sumbittime;
    }

    public void setSumbittime(Date sumbittime) {
        this.sumbittime = sumbittime;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Date getAudtime() {
        return audtime;
    }

    public void setAudtime(Date audtime) {
        this.audtime = audtime;
    }

    public BigDecimal getDfee() {
        return dfee;
    }

    public void setDfee(BigDecimal dfee) {
        this.dfee = dfee;
    }

    public String getDfeesymbol() {
        return dfeesymbol;
    }

    public void setDfeesymbol(String dfeesymbol) {
        this.dfeesymbol = dfeesymbol;
    }

    public Integer getIdcardpass() {
        return idcardpass;
    }

    public void setIdcardpass(Integer idcardpass) {
        this.idcardpass = idcardpass;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }
}
