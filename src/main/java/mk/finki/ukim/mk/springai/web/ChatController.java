package mk.finki.ukim.mk.springai.web;

import mk.finki.ukim.mk.springai.service.ImageService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ai")
public class ChatController {
    private final OpenAiChatModel chatModel;
    private final ImageService imageService;

    public ChatController(OpenAiChatModel chatModel, ImageService imageService) {
        this.chatModel = chatModel;
        this.imageService = imageService;
    }

    @GetMapping
    public String chatInput(){
        return "chatInput";
    }
    @PostMapping("/chat")
    public String askAi(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message, Model model){
        String responseAi = this.chatModel.call(message);
        model.addAttribute("responseAi", responseAi);
        return "chat";
    }
    @GetMapping("/image")
    public String image(){
        return "image";
    }
    @PostMapping("/image/generate")
    public String imageGenerated(@RequestParam(value = "message", defaultValue = "dog") String message, Model model){
        ImageResponse imageResponse = imageService.generateImage(message);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        model.addAttribute("imageUrl", imageUrl);
        return "imageResponse";
    }
}
