package com.soutvoid.tvpr.domain.genre;

import org.springframework.data.repository.CrudRepository;

public interface GenresRepository extends CrudRepository<Genre, Long> {

    Genre findByName(String name);

    void deleteByName(String name);

}
