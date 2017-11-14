package com.soutvoid.tvpr.service;

import com.soutvoid.tvpr.domain.channel.Channel;
import com.soutvoid.tvpr.domain.channel.ChannelsRepository;
import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.genre.GenresRepository;
import com.soutvoid.tvpr.domain.schedule.ChannelSchedule;
import com.soutvoid.tvpr.domain.schedule.SchedulesRepository;
import com.soutvoid.tvpr.domain.show.Show;
import com.soutvoid.tvpr.domain.show.ShowForm;
import com.soutvoid.tvpr.domain.show.ShowsRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainService {

    private ChannelsRepository channelsRepository;
    private GenresRepository genresRepository;
    private SchedulesRepository schedulesRepository;
    private ShowsRepository showsRepository;

    @Inject
    public MainService(ChannelsRepository channelsRepository,
                       GenresRepository genresRepository,
                       SchedulesRepository schedulesRepository,
                       ShowsRepository showsRepository) {
        this.channelsRepository = channelsRepository;
        this.genresRepository = genresRepository;
        this.schedulesRepository = schedulesRepository;
        this.showsRepository = showsRepository;
    }

    public List<Genre> getAllGenres() {
        return iterableToList(genresRepository.findAll());
    }

    public Genre findGenreByName(String name) {
        return genresRepository.findByName(name);
    }

    public List<Channel> getAllChannels() {
        return iterableToList(channelsRepository.findAll());
    }

    public Channel findChannelByName(String name) {
        return channelsRepository.findByName(name);
    }

    public List<Show> getAllShows() {
        return iterableToList(showsRepository.findAll());
    }

    public List<ChannelSchedule> getAllSchedules() {
        return iterableToList(schedulesRepository.findAll());
    }

    public Show getShowByChannelAndStartTime(String channelName, String startTime) {
        Channel channel = findChannelByName(channelName);
        if (channel != null) {
            List<Show> shows = channel.getSchedule()
                    .getShows()
                    .stream().filter(show -> show.getFormattedTime(show.getStartTime()).equals(startTime)).collect(Collectors.toList());
            if (shows != null && !shows.isEmpty())
                return shows.get(0);
        }
        return null;
    }

    public void addGenre(String name) {
        genresRepository.save(new Genre(name));
    }

    public void addShow(ShowForm showForm, String channelName) {
        Channel channel = findChannelByName(channelName);
        if (channel != null) {
            Show show = showForm.getShow(getAllGenres());
            show.setSchedule(channel.getSchedule());
            showsRepository.save(show);
        }
    }

    public void addShow(String name,
                        int dayOfWeek,
                        String startTime,
                        String hours,
                        String minutes,
                        String genreName,
                        String channelName) {
        ShowForm showForm = new ShowForm();
        showForm.setName(name);
        showForm.setDayOfWeek(dayOfWeek);
        showForm.setStartTime(startTime);
        showForm.setHours(hours);
        showForm.setMinutes(minutes);
        showForm.setGenreName(genreName);
        addShow(showForm, channelName);
    }

    public void addChannel(String name) {
        channelsRepository.save(new Channel(name));
    }

    public void saveShow(Show show) {
        showsRepository.save(show);
    }

    public void saveChannel(Channel channel) {
        channelsRepository.save(channel);
    }

    public void saveGenre(Genre genre) {
        genresRepository.save(genre);
    }

    public void deleteGenre(String name) {
        genresRepository.deleteByName(name);
    }

    public void deleteShowByChannelNameAndStartTime(String channelName, String name) {
        showsRepository.delete(getShowByChannelAndStartTime(channelName, name));
    }

    public void deleteChannelByName(String name) {
        channelsRepository.deleteByName(name);
    }

    private <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }
}
