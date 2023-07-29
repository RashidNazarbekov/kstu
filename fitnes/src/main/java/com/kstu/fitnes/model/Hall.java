package com.kstu.fitnes.model;

public class Hall {
    private Long hallId;
    private String name;

    public Hall() {
    }

    public Hall(String name) {
        this.name = name;
    }

    public Hall(Long hallId, String name) {
        this.hallId = hallId;
        this.name = name;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallId=" + hallId +
                ", name='" + name + '\'' +
                '}';
    }
}
