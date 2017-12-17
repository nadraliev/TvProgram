package com.soutvoid.tvpr.controller;

import com.soutvoid.tvpr.domain.channel.ChannelsRepository;
import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.genre.GenresRepository;
import com.soutvoid.tvpr.domain.show.ShowsRepository;
import com.soutvoid.tvpr.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GenresController {

    private GenresRepository genresRepository;

    public GenresController(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @ModelAttribute("genres")
    public List<Genre> genres() {
        return Utils.iterableToList(genresRepository.findAll());
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

    @RequestMapping(value = "/genres", method = RequestMethod.PUT)
    @ResponseBody
    public boolean newGenre(@RequestBody String name) {
        List<Genre> genres = Utils.iterableToList(genresRepository.findAll());
        if (Utils.validate(name) && genres.stream().noneMatch(x -> x.getName().equals(name)))
            genresRepository.save(new Genre(name));
        return true;
    }

    @RequestMapping(value = "/genres/{index}", method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteGenre(@PathVariable String index) {
        List<Genre> genres = Utils.iterableToList(genresRepository.findAll());
        Genre genre = genres.get(Integer.parseInt(index));
        genresRepository.delete(genre);
        return true;
    }
}
