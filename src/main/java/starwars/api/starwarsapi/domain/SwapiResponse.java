package starwars.api.starwarsapi.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwapiResponse {
    private int count;
    private Object next;
    private Object previous;
    private List<Movie> results;
}
