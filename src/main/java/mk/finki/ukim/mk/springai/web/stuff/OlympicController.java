package mk.finki.ukim.mk.springai.web.stuff;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
@RequestMapping("/olympics")
public class OlympicController {

    private final ChatClient chatClient;
    @Value("classpath:/docs/olympic-sports.txt")
    private Resource docsToStuffResource;
    @Value("classpath:/prompts/olympic-sports.st")
    private Resource olympicSportsResource;

    public OlympicController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/2024")
    public String get2024OlympicSports(
            @RequestParam(value = "message", defaultValue = "What sports are being included in the 2024 Summer Olympics?") String message,
            @RequestParam(value = "stuff", defaultValue = "false") boolean stuffFlag
    ) throws IOException {
        String sports = docsToStuffResource.getContentAsString(Charset.defaultCharset());
        return chatClient.prompt()
                .user(u -> {
                    u.text(olympicSportsResource);
                    u.param("question",message);
                    u.param("context", stuffFlag ? sports : "");
                })
                .call()
                .content();
    }
}
