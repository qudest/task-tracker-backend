package task.tracker.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfiguration {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        org.springframework.web.cors.CorsConfiguration cors = new org.springframework.web.cors.CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.setAllowedOrigins(
                //todo вынести
                List.of("http://localhost:5173")
        );
        cors.setAllowedHeaders(List.of(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));
        cors.setAllowedMethods(List.of(
                HttpMethod.HEAD.name(),
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));
        cors.setExposedHeaders(List.of(
                HttpHeaders.AUTHORIZATION
        ));
        cors.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

}
