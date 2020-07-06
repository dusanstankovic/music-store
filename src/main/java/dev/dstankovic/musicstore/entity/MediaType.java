package dev.dstankovic.musicstore.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mediatype", schema = "chinook")
public class MediaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaTypeId", nullable = false)
    private int id;

    @NotNull(message = "Media type name is required")
    @Size(min = 1, message = "Media type name must contain one or more characters")
    @Column(name = "Name", length = 120)
    private String name;

    public MediaType() {
    }

    public MediaType(String name) {
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
