package mk.finki.ukim.mk.springai.web.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ActorFilms {
    String actor;
    List<String> movies;

    public ActorFilms(String actor, List<String> movies) {
        this.actor = actor;
        this.movies = movies;
    }
}
