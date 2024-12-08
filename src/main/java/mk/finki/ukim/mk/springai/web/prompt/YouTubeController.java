package mk.finki.ukim.mk.springai.web.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/youtube")
public class YouTubeController {
    private final ChatClient chatClient;
    @Value("classpath:/prompts/youtube.st")
    private Resource youtubePromptResource;
    public YouTubeController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/popular")
    public String findPopularYouTubersStepOne(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
        String message = """
            List 10 of the most popular YouTubers in {genre}.
            If you don't know the answer , just say "I don't know".
            """;

        return chatClient.prompt()
                .user(u -> u.text(message).param("genre",genre))
                .call()
                .content();
    }

    @GetMapping("/popular-resource")
    public String findPopularYouTubers(@RequestParam(value = "genre", defaultValue = "tech") String genre) {
        return chatClient.prompt()
                .user(u -> u.text(youtubePromptResource).param("genre",genre))
                .call()
                .content();
    }
}
