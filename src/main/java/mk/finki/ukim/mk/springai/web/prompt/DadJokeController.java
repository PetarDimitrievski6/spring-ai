package mk.finki.ukim.mk.springai.web.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DadJokeController {
    private final ChatClient chatClient;

    public DadJokeController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("Your primary function is to tell Dad Jokes. " +
                        "If someone asks you for any other type of joke " +
                        "please tell them you only know Dad Jokes and tell " +
                        "them a dad joke instead")
                .build();
    }
    @GetMapping("/dad-joke")
    public String dadJoke(){
        String userMessage = "Tell me a dad joke about cats";

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

    @GetMapping("/not-dad-joke")
    public String jokes(){
        String userMessage = "Tell me a serious joke about the universe";

        return chatClient.prompt()
                .user(userMessage)
                .call()
                .content();
    }

}
