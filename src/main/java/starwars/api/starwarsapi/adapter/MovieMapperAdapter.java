package starwars.api.starwarsapi.adapter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import starwars.api.starwarsapi.domain.Movie;
import starwars.api.starwarsapi.resource.dto.MovieRequestDto;

@Mapper
public interface MovieMapperAdapter {
    MovieMapperAdapter INSTANCE = Mappers.getMapper(MovieMapperAdapter.class);
    Movie convert(MovieRequestDto movieRequestDto);
}
