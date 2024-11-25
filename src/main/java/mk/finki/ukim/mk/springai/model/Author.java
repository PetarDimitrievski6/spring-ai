package mk.finki.ukim.mk.springai.model;

import lombok.Data;

import java.util.List;

@Data
public class Author {
    String name;
    List<String> books;
}
