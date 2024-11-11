package mk.finki.ukim.mk.springai.service;


import org.springframework.ai.image.*;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageModel imageModel;

    public ImageService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt) {
        ImageOptions imageOptions = ImageOptionsBuilder.builder()
                .withN(1) //Number of images to be generated
                .withHeight(1024)
                .withWidth(1024)
                .build();




        return imageModel.call(new ImagePrompt(prompt, imageOptions));
    }
}