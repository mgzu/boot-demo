package com.example.fsm.business.config.jpa;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author MaGuangZu
 * @since 2021-12-29
 */
@EnableJpaAuditing
@Configuration
public class UserIDAuditorBean implements AuditorAware<String> {
    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("1640600092347");
    }
}
