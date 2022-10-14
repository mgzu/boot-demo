package com.example.app.order.configure;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RuleEngine 配置
 *
 * @author MaGuangZu
 * @since 2022-10-14
 */
@Configuration
public class RuleEngineConfigure {

    @Bean
    public RulesEngine rulesEngine() {
        RulesEngineParameters parameters = new RulesEngineParameters();
        return new DefaultRulesEngine(parameters);
    }

}
