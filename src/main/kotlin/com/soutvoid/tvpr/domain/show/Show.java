package com.soutvoid.tvpr.domain.show;

import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.schedule.ChannelSchedule;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Show implements Serializable {

    private long id = 0;
    private String name = "";
    private int dayOfWeek = 0;
    private long startTime = 0;
    private long duration = 0;
    private Genre genre = null;
    private ChannelSchedule schedule = null;

    public Show() {
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

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @ManyToOne
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @ManyToOne
    @Ignore
    public ChannelSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(ChannelSchedule schedule) {
        this.schedule = schedule;
    }

    public String getFormattedTime(long minutes) {
        return String.format("%02d", minutes / 60) + ":" + String.format("%02d", minutes % 60);
    }


}
