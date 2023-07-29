package com.kstu.fitnes.model;

public class Accounting {
    private  Long accountingId;
    private Long clientId;
    private Long abonementId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    private String month;
    private boolean status_oplaty;

    public Accounting() {
    }

    public Accounting(Long abonementId, String month, boolean status_oplaty) {
        this.abonementId = abonementId;
        this.month = month;
        this.status_oplaty = status_oplaty;
    }

    public Accounting(Long clientId, Long abonementId, String month, boolean status_oplaty) {
        this.clientId = clientId;
        this.abonementId = abonementId;
        this.month = month;
        this.status_oplaty = status_oplaty;
    }

    public Accounting(Long accountingId, Long clientId, Long abonementId, String month, boolean status_oplaty) {
        this.accountingId = accountingId;
        this.clientId = clientId;
        this.abonementId = abonementId;
        this.month = month;
        this.status_oplaty = status_oplaty;
    }

    public Long getAccountingId() {
        return accountingId;
    }

    public void setAccountingId(Long accountingId) {
        this.accountingId = accountingId;
    }

    public Long getAbonementId() {
        return abonementId;
    }

    public void setAbonementId(Long abonementId) {
        this.abonementId = abonementId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public boolean isStatus_oplaty() {
        return status_oplaty;
    }

    public boolean getStatus_oplaty() {
        return status_oplaty;
    }

    public void setStatus_oplaty(boolean status_oplaty) {
        this.status_oplaty = status_oplaty;
    }

    @Override
    public String toString() {
        return "Accounting{" +
                "accountingId=" + accountingId +
                ", abonementId=" + abonementId +
                ", month='" + month + '\'' +
                ", status_oplaty=" + status_oplaty +
                '}';
    }
}
