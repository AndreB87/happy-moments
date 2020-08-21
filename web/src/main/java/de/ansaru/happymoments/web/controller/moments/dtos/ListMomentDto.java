package de.ansaru.happymoments.web.controller.moments.dtos;

public class ListMomentDto {

    private long id;

    private String name;

    public ListMomentDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
