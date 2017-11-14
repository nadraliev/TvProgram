package com.soutvoid.tvpr.domain.schedule;

import com.soutvoid.tvpr.domain.show.Show;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChannelSchedule implements Serializable {

    private long id = 0;
    private List<Show> shows = new ArrayList<>();

    public ChannelSchedule() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("startTime ASC")
    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

}
