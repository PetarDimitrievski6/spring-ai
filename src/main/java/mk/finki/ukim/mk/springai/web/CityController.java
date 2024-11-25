package mk.finki.ukim.mk.springai.web;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    private final ChatClient chatClient;

    public CityController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/cities")
    public String cities(@RequestParam(value = "message") String message){
        SystemMessage systemMessage = new SystemMessage("You are a helpful AI assistant answering questions about cities around the world.");
        UserMessage userMessage = new UserMessage(message);

        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withFunction("currentWeatherFunction")
                .build();

        return chatClient.prompt(new Prompt(List.of(systemMessage, userMessage), chatOptions)).call().content();
    }
}
