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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShowsController {

    private ShowsRepository showsRepository;
    private ChannelsRepository channelsRepository;
    private GenresRepository genresRepository;

    public ShowsController(ShowsRepository showsRepository, ChannelsRepository channelsRepository, GenresRepository genresRepository) {
        this.showsRepository = showsRepository;
        this.channelsRepository = channelsRepository;
        this.genresRepository = genresRepository;
    }

    @RequestMapping(value = "/channels/{id}/shows",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public boolean newShow(@PathVariable String id,
                           @ModelAttribute("ShowForm") ShowForm showForm) {
        Channel channel = channelsRepository.findOne(Long.parseLong(id));
        List<Genre> genres = Utils.iterableToList(genresRepository.findAll());
        if (Utils.validate(showForm, genres)) {
            Show show = Utils.getShow(showForm, genres);
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
}
