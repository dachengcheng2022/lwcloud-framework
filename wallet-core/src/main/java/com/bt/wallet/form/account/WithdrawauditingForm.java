package com.bt.wallet.form.account;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawauditingForm {
    private Integer auditid;
    private BigDecimal dquantity;
    private BigDecimal dfee;
    private String toaddress;
    private Integer audstatus;
    private Date audtime;
    private Date sumbittime;
    private String symbol;
    private String txhash;
    private String dfeesymbol;

    public Integer getAuditid() {
        return auditid;
    }

    public void setAuditid(Integer auditid) {
        this.auditid = auditid;
    }

    public BigDecimal getDquantity() {
        return dquantity;
    }

    public void setDquantity(BigDecimal dquantity) {
        this.dquantity = dquantity;
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

    public Integer getAudstatus() {
        return audstatus;
    }

    public void setAudstatus(Integer audstatus) {
        this.audstatus = audstatus;
    }

    public Date getAudtime() {
        return audtime;
    }

    public void setAudtime(Date audtime) {
        this.audtime = audtime;
    }

    public Date getSumbittime() {
        return sumbittime;
    }

    public void setSumbittime(Date sumbittime) {
        this.sumbittime = sumbittime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
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

    @Override
    public String toString() {
        return "WithdrawauditingForm{" +
                "auditid=" + auditid +
                ", dquantity=" + dquantity +
                ", dfee=" + dfee +
                ", toaddress='" + toaddress + '\'' +
                ", audstatus=" + audstatus +
                ", audtime=" + audtime +
                ", sumbittime=" + sumbittime +
                ", csymbol='" + symbol + '\'' +
                ", txhash='" + txhash + '\'' +
                '}';
    }
}
