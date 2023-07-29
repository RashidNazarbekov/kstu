package com.kstu.fitnes.model;

public class Instructor {
    private Long instructorId;
    private String lastName;
    private String firstName;
    private double oklad;

    public Instructor() {
    }

    public Instructor(String lastName, String firstName, double oklad) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.oklad = oklad;
    }

    public Instructor(Long instructorId, String lastName, String firstName, double oklad) {
        this.instructorId = instructorId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.oklad = oklad;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
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

    public double getOklad() {
        return oklad;
    }

    public void setOklad(double oklad) {
        this.oklad = oklad;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", oklad=" + oklad +
                '}';
    }
}
