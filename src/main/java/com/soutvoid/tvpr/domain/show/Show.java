package com.soutvoid.tvpr.domain.show;

import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.schedule.ChannelSchedule;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Show implements Serializable {

    private String name = "";
    private int dayOfWeek = 0;
    private long startTime = 0;
    private long duration = 0;
    private Genre genre = null;
    private ChannelSchedule schedule = null;
    private long id;

    public Show(String name, int dayOfWeek, long startTime, long duration, Genre genre, ChannelSchedule schedule, long id) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.duration = duration;
        this.genre = genre;
        this.schedule = schedule;
        this.id = id;
    }

    public Show(String name, int dayOfWeek, long startTime, long duration, Genre genre, ChannelSchedule schedule) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.duration = duration;
        this.genre = genre;
        this.schedule = schedule;
    }

    public Show(String name, int dayOfWeek, long startTime, long duration, Genre genre) {
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.duration = duration;
        this.genre = genre;
    }

    public Show() {
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormattedTime(long minutes) {
        return String.format("%02d", minutes / 60) +
                ":" + String.format("%02d", minutes % 60);
    }
}
