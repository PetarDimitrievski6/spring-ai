package mk.finki.ukim.mk.springai.web.vision;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class VisionController {

    private final ChatClient chatClient;
    @Value("classpath:/images/coffee.jpg")
    Resource coffeeImage;
    @Value("classpath:/images/code.png")
    Resource codeImage;

    public VisionController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    @GetMapping("/coffee-describe")
    public String describeCoffee() throws IOException {
        return chatClient.prompt()
                .user(u -> u
                        .text("Can you please explain what you see in the following image?")
                        .media(MimeTypeUtils.IMAGE_JPEG,coffeeImage)
                )
                .call()
                .content();
    }
    @GetMapping("/code-describe")
    public String describeCode() throws IOException {
        return chatClient.prompt()
                .user(u -> u
                        .text("Can you please explain what you see in the following image?")
                        .media(MimeTypeUtils.IMAGE_PNG,codeImage)
                )
                .call()
                .content();
    }
    @GetMapping("/image-to-code")
    public String imageToCode() throws IOException {
        return chatClient.prompt()
                .user(u -> u
                        .text("The following is a screenshot of some code. Can you translate this from image into actual code.")
                        .media(MimeTypeUtils.IMAGE_PNG,codeImage)
                )
                .call()
                .content();
    }
}

