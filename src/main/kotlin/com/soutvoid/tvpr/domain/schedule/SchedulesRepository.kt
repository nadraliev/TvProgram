package com.soutvoid.tvpr.domain.schedule

import org.springframework.data.repository.CrudRepository

interface SchedulesRepository: CrudRepository<ChannelSchedule, Long>