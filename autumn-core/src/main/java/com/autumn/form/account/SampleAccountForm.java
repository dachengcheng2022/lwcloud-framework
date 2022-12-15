package com.autumn.form.account;

import java.math.BigDecimal;

public class SampleAccountForm {
    private String symbol;
    private BigDecimal ubalance;
    private BigDecimal fbalance;
    private BigDecimal unbalance;
    private BigDecimal receivedquantity;
    private BigDecimal expirequantity;
    private Integer cointype;
    private String ciconpath;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getUbalance() {
        return ubalance;
    }

    public void setUbalance(BigDecimal ubalance) {
        this.ubalance = ubalance;
    }

    public BigDecimal getFbalance() {
        return fbalance;
    }

    public void setFbalance(BigDecimal fbalance) {
        this.fbalance = fbalance;
    }

    public BigDecimal getUnbalance() {
        return unbalance;
    }

    public void setUnbalance(BigDecimal unbalance) {
        this.unbalance = unbalance;
    }

    public BigDecimal getReceivedquantity() {
        return receivedquantity;
    }

    public void setReceivedquantity(BigDecimal receivedquantity) {
        this.receivedquantity = receivedquantity;
    }

    public BigDecimal getExpirequantity() {
        return expirequantity;
    }

    public void setExpirequantity(BigDecimal expirequantity) {
        this.expirequantity = expirequantity;
    }

    public Integer getCointype() {
        return cointype;
    }

    public void setCointype(Integer cointype) {
        this.cointype = cointype;
    }

    public String getCiconpath() {
        return ciconpath == null ? this.symbol + ".png" : ciconpath;
    }

    public void setCiconpath(String ciconpath) {
        this.ciconpath = ciconpath;
    }

    @Override
    public String toString() {
        return "SampleAccountForm{" +
                "csymbol='" + symbol + '\'' +
                ", ubalance=" + ubalance +
                ", fbalance=" + fbalance +
                ", unbalance=" + unbalance +
                ", receivedquantity=" + receivedquantity +
                ", expirequantity=" + expirequantity +
                ", cointype=" + cointype +
                '}';
    }
}
