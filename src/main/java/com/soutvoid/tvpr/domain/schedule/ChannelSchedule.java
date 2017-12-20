package com.soutvoid.tvpr.domain.schedule;

import com.soutvoid.tvpr.domain.channel.Channel;
import com.soutvoid.tvpr.domain.show.Show;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChannelSchedule implements Serializable {

    private List<Show> shows = new ArrayList<>();
    private Channel channel = null;
    private long id;

    public ChannelSchedule(List<Show> shows, long id) {
        this.shows = shows;
        this.id = id;
    }

    public ChannelSchedule(Channel channel) {
        this.channel = channel;
    }

    public ChannelSchedule() {
    }

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("startTime ASC")
    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    @OneToOne
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
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
