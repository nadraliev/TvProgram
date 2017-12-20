package com.soutvoid.tvpr.domain.channel;

import com.soutvoid.tvpr.domain.schedule.ChannelSchedule;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Channel implements Serializable {

    private String name = "";
    private ChannelSchedule schedule = new ChannelSchedule(this);
    private long id;

    public Channel(String name, ChannelSchedule schedule, long id) {
        this.name = name;
        this.schedule = schedule;
        this.id = id;
    }

    public Channel(String name) {
        this.name = name;
    }

    public Channel() {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
