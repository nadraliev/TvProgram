package com.soutvoid.tvpr.util;

import com.soutvoid.tvpr.domain.genre.Genre;
import com.soutvoid.tvpr.domain.show.Show;
import com.soutvoid.tvpr.domain.show.ShowForm;

import java.util.ArrayList;
import java.util.List;

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

}
