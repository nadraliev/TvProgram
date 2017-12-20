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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, @RequestParam("query") String query) {
        if (Utils.validate(query)) {
            List<Show> shows = showsRepository.findAllByNameOrGenreNameOrderByScheduleChannelNameAsc(query, query);
            model.addAttribute("shows", shows);
            return "fragments/shows :: shows";
        }
        return "fragments/channels :: channels";
    }

}
