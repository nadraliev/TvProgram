package com.soutvoid.tvpr.domain.genre;

import com.soutvoid.tvpr.domain.show.Show;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre implements Serializable {

    private String name = "";
    private Set<Show> shows = new HashSet<>();
    private long id;

    public Genre(String name, Set<Show> shows, long id) {
        this.name = name;
        this.shows = shows;
        this.id = id;
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    @Ignore
    public Set<Show> getShows() {
        return shows;
    }

    public void setShows(Set<Show> shows) {
        this.shows = shows;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
