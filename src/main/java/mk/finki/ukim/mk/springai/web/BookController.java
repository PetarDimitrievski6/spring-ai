package mk.finki.ukim.mk.springai.web;

import mk.finki.ukim.mk.springai.model.Author;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("books")
public class BookController {
    private final ChatClient chatClient;

    public BookController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/by-author")
    public Author getBooksByAuthor(@RequestParam(value = "author", defaultValue = "Robert Greene") String author){
        String promptMessage = """
                Generate a list of books written by the author {author}. If you aren't positive that a book
                belongs to this author please don't include it;
                {format}
                """;
        BeanOutputConverter<Author> outputConverter = new BeanOutputConverter<>(Author.class);
        String format = outputConverter.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage, Map.of("author", author, "format", format));
        Prompt prompt = promptTemplate.create();
        Generation generation = chatClient.prompt(prompt).call().chatResponse().getResult();
        return outputConverter.convert(generation.getOutput().getContent());
    }

    @GetMapping("/author/{author}")
    public Map<String, Object> getAuthorsSocialLinks(@PathVariable String author){
        String promptMessage = """
                Generate a list of links for author {author}. Include the authors name as key and any social 
                network links as the object.
                {format}
                """;
        MapOutputConverter outputConverter = new MapOutputConverter();
        String format = outputConverter.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage, Map.of("author", author, "format", format));
        Prompt prompt = promptTemplate.create();
        Generation generation = chatClient.prompt(prompt).call().chatResponse().getResult();
        return outputConverter.convert(generation.getOutput().getContent());
    }

}
