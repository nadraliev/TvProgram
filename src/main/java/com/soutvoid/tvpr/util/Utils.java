package com.soutvoid.tvpr.util;

import com.soutvoid.tvpr.domain.channel.Channel;
import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.show.Show;
import com.soutvoid.tvpr.domain.show.ShowForm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static int getMinutes(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 +
                Integer.parseInt(time.substring(3, 5));
    }

    public static boolean validate(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean validate(ShowForm showForm, List<Genre> genres) {
        try {
            Long.parseLong(showForm.getHours());
            Long.parseLong(showForm.getMinutes());
        } catch (NumberFormatException e) {
            return false;
        }
        return validate(showForm.getName()) &&
                (showForm.getDayOfWeek() >= 0 && showForm.getDayOfWeek() <= 6) &&
                genres.stream().anyMatch(x -> x.getName().equals(showForm.getGenreName()));
    }

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> result = new ArrayList<>();
        iterable.forEach(x -> result.add(x));
        return result;
    }

    public static Show getShow(ShowForm showForm, List<Genre> allGenres) {
        return new Show(
                showForm.getName(),
                showForm.getDayOfWeek(),
                Utils.getMinutes(showForm.getStartTime()),
                Long.parseLong(showForm.getHours()) * 60 + Long.parseLong(showForm.getMinutes()),
                allGenres.stream().filter(x -> x.getName().equals(showForm.getGenreName())).findFirst().get()
        );
    }

    public static Channel filter(Channel channel, String query) {
        int time = -1;
        if (query.contains(":")) {
            int hours = -1;
            int minutes = -1;
            try {
                hours = Integer.parseInt(query.substring(0, query.indexOf(":")));
                minutes = Integer.parseInt(query.substring(query.indexOf(":") + 1, query.length()));
                time = hours * 60 + minutes;
            } catch (Exception e) {}
        }
        final int searchTime = time;
        channel.getSchedule().setShows(
                channel.getSchedule().getShows().stream().filter(show ->
                        show.getName().equalsIgnoreCase(query) || show.getGenre().getName().equalsIgnoreCase(query)
                        || (searchTime >= 0 && searchTime >= show.getStartTime() && searchTime <= show.getStartTime() + show.getDuration())
                ).collect(Collectors.toList())
        );
        return channel;
    }

}
