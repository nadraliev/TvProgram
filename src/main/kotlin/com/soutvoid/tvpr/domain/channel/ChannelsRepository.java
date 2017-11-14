package com.soutvoid.tvpr.domain.channel;

import org.springframework.data.repository.CrudRepository;

public interface ChannelsRepository extends CrudRepository<Channel, Long> {

    Channel findByName(String name);

    void deleteByName(String name);

}
