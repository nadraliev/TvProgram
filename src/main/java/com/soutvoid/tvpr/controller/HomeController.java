package com.soutvoid.tvpr.controller;

import com.soutvoid.tvpr.domain.channel.Channel;
import com.soutvoid.tvpr.domain.channel.ChannelsRepository;
import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.genre.GenresRepository;
import com.soutvoid.tvpr.domain.show.Show;
import com.soutvoid.tvpr.domain.show.ShowForm;
import com.soutvoid.tvpr.domain.show.ShowsRepository;
import com.soutvoid.tvpr.util.Utils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/")
@Controller
public class HomeController {

    private GenresRepository genresRepository;
    private ShowsRepository showsRepository;
    private ChannelsRepository channelsRepository;

    @Inject
    public HomeController(GenresRepository genresRepository, ShowsRepository showsRepository, ChannelsRepository channelsRepository) {
        this.genresRepository = genresRepository;
        this.showsRepository = showsRepository;
        this.channelsRepository = channelsRepository;
    }

    @ModelAttribute("genres")
    public List<Genre> genres() {
        return Utils.iterableToList(genresRepository.findAll());
    }

    @ModelAttribute("channels")
    public List<Channel> channels() {
        return Utils.iterableToList(channelsRepository.findAll());
    }

    @ModelAttribute("weekDays")
    public List<String> weekDays() {
        return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::toString).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/genres/names", method = RequestMethod.GET)
    @ResponseBody
    public List<String> allGenresNames(Model model) {
        return Utils.iterableToList(genresRepository.findAll()).stream()
                .map(Genre::getName).collect(Collectors.toList());
    }

    @RequestMapping(value = "genres", method = RequestMethod.GET)
    public String genres(Model model) {
        return "fragments/genres::genres";
    }

    @RequestMapping(value = "/channels", method = RequestMethod.POST)
    public String channels(Model model) {
        return "fragments/channels :: channels";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam("query") String query) {
        if (Utils.validate(query)) {
            List<Channel> channelList = channels().stream()
                    .map(channel -> Utils.filter(channel, query))
                    .filter(channel -> !channel.getSchedule().getShows().isEmpty())
                    .collect(Collectors.toList());
            model.addAttribute("channels", channelList);
        }
        return "fragments/channels :: channels";
    }

    @RequestMapping(value = "/genres", method = RequestMethod.PUT)
    @ResponseBody
    public boolean newGenre(@RequestBody String name) {
        if (Utils.validate(name) && genres().stream().noneMatch(x -> x.getName().equals(name)))
            genresRepository.save(new Genre(name));
        return true;
    }

    @RequestMapping(value = "/genres/{index}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteGenre(@PathVariable String index) {
        Genre genre = genres().get(Integer.parseInt(index));
        genresRepository.delete(genre);
        return true;
    }

    @RequestMapping(value = "/channels/{id}/shows",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public boolean newShow(@PathVariable String id,
                           @ModelAttribute("ShowForm") ShowForm showForm) {
        Channel channel = channelsRepository.findOne(Long.parseLong(id));
        if (Utils.validate(showForm, genres())) {
            Show show = Utils.getShow(showForm, genres());
            show.setSchedule(channel.getSchedule());
            showsRepository.save(show);
        }
        return true;
    }

    @RequestMapping(value = "/channels/{id}/shows/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteShow(@PathVariable String id) {
        showsRepository.delete(Long.parseLong(id));
        return true;
    }

    @RequestMapping(value = "/channels", method = RequestMethod.PUT)
    @ResponseBody
    public boolean newChannel(@RequestBody String name) {
        if (Utils.validate(name) && channels().stream().noneMatch(x -> x.getName().equals(name)))
            channelsRepository.save(new Channel(name));
        return true;
    }

    @RequestMapping(value = "/channels/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteChannel(@PathVariable String id) {
        channelsRepository.delete(Long.parseLong(id));
        return true;
    }
}
