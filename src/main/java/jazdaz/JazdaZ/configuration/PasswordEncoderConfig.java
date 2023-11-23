package jazdaz.JazdaZ.configuration;

import jazdaz.JazdaZ.utils.password.PasswordEncoderMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoderMapper passwordEncoderMapper() {
        return new PasswordEncoderMapper(passwordEncoder());
    }
}
