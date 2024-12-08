package mk.finki.ukim.mk.springai.web.function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionConfiguration {
    private final WeatherConfigProperties properties;

    public FunctionConfiguration(WeatherConfigProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Description("Get the current weather conditions for the given city.")
    public Function<WeatherService.Request, WeatherService.Response> currentWeatherFunction(){
        return new WeatherService(properties);
    }
}
