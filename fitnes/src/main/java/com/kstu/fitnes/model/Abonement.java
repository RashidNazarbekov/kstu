package com.kstu.fitnes.model;

public class Abonement {
    private Long abonementId;
    private String description;
    private Double price;
    private Long hall;

    public Abonement() {
    }

    public Abonement(String description, Double price, Long hall) {
        this.description = description;
        this.price = price;
        this.hall = hall;
    }

    public Abonement(Long abonementId, String description, Double price, Long hall) {
        this.abonementId = abonementId;
        this.description = description;
        this.price = price;
        this.hall = hall;
    }

    public Long getAbonementId() {
        return abonementId;
    }

    public void setAbonementId(Long abonementId) {
        this.abonementId = abonementId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getHall() {
        return hall;
    }

    public void setHall(Long hall) {
        this.hall = hall;
    }

    @Override
    public String toString() {
        return "Abonement{" +
                "abonementId=" + abonementId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", hall=" + hall +
                '}';
    }
}
