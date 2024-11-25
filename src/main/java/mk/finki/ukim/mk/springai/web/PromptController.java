package mk.finki.ukim.mk.springai.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromptController {
    private final ChatClient chatClient;

    public PromptController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    @GetMapping("")
    public String simple(){
        return chatClient.prompt(new Prompt("Tell me a joke"))
                .call()
                .content();
    }
}
