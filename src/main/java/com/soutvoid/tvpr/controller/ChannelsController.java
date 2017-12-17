package com.soutvoid.tvpr.controller;

import com.soutvoid.tvpr.domain.channel.Channel;
import com.soutvoid.tvpr.domain.channel.ChannelsRepository;
import com.soutvoid.tvpr.domain.genre.GenresRepository;
import com.soutvoid.tvpr.domain.show.ShowsRepository;
import com.soutvoid.tvpr.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ChannelsController {

    private ChannelsRepository channelsRepository;

    public ChannelsController(ChannelsRepository channelsRepository) {
        this.channelsRepository = channelsRepository;
    }

    @ModelAttribute("channels")
    public List<Channel> channels() {
        return Utils.iterableToList(channelsRepository.findAll());
    }

    @ModelAttribute("weekDays")
    public List<String> weekDays() {
        return Arrays.stream(DayOfWeek.values()).map(DayOfWeek::toString).collect(Collectors.toList());
    }

    @RequestMapping(value = "/channels", method = RequestMethod.POST)
    public String channels(Model model) {
        return "fragments/channels :: channels";
    }

    @RequestMapping(value = "/channels", method = RequestMethod.PUT)
    @ResponseBody
    public boolean newChannel(@RequestBody String name) {
        List<Channel> channels = Utils.iterableToList(channelsRepository.findAll());
        if (Utils.validate(name) && channels.stream().noneMatch(x -> x.getName().equals(name)))
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
