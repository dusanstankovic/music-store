package dev.dstankovic.musicstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mediatype", schema = "chinook")
public class MediaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaTypeId", nullable = false)
    private int id;

    @NotEmpty(message = "Media type name is required")
    @Column(name = "Name", length = 120)
    private String name;

    public MediaType() {
    }

    public MediaType(@NotEmpty(message = "Media type name is required") String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MediaType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
