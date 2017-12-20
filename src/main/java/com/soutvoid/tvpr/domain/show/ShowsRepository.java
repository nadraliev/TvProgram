package com.soutvoid.tvpr.domain.show;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShowsRepository extends CrudRepository<Show, Long> {

    List<Show> findAllByNameOrGenreNameOrderByScheduleChannelNameAsc(String name, String genreName);

}
