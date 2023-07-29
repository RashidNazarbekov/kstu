package com.kstu.fitnes.model;

public class AccountingClient {

    private Long clientId;
    private String lastName;
    private String firstName;
    private boolean status_oplaty;

    public AccountingClient() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isStatus_oplaty() {
        return status_oplaty;
    }

    public void setStatus_oplaty(boolean status_oplaty) {
        this.status_oplaty = status_oplaty;
    }
}
