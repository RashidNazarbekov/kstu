package com.kstu.fitnes.model;

public class Client {
    private Long clientId;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private Long instructor;

    public Client() {
    }

    public Client(String lastName, String firstName, String phoneNumber, Long instructor) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.instructor = instructor;
    }

    public Client(Long clientId, String lastName, String firstName, String phoneNumber, Long instructor) {
        this.clientId = clientId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.instructor = instructor;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getInstructor() {
        return instructor;
    }

    public void setInstructor(Long instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", instructor=" + instructor +
                '}';
    }
}
