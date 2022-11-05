package com.bt.wallet.form.transfer;


import java.math.BigDecimal;

public class TransferParams {
    private int userId;

    private String transferId;

    private int coinid;

    private int fromApplId;

    private int toApplId;

    private BigDecimal quantity;

    private String id;

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCoinid() {
        return coinid;
    }

    public void setCoinid(int coinid) {
        this.coinid = coinid;
    }

    public int getFromApplId() {
        return fromApplId;
    }

    public void setFromApplId(int fromApplId) {
        this.fromApplId = fromApplId;
    }

    public int getToApplId() {
        return toApplId;
    }

    public void setToApplId(int toApplId) {
        this.toApplId = toApplId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
