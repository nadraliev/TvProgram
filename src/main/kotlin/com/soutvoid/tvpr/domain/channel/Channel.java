package com.soutvoid.tvpr.domain.channel;

import com.soutvoid.tvpr.domain.schedule.ChannelSchedule;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Channel implements Serializable {

    private long id = 0;
    private String name = "";
    private ChannelSchedule schedule = new ChannelSchedule();

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(cascade = CascadeType.ALL)
    public ChannelSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(ChannelSchedule schedule) {
        this.schedule = schedule;
    }
}
