package net.betvictor.core.mapper;

import net.betvictor.core.dto.StatisticDto;
import net.betvictor.core.service.kafka.model.StatisticModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatisticMapping {
    StatisticModel toModel(StatisticDto dto);
}
