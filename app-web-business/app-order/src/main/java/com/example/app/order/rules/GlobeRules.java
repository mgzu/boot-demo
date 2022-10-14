package com.example.app.order.rules;

import org.jeasy.rules.api.Rules;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MaGuangZu
 * @since 2022-10-14
 */
public class GlobeRules {
    protected static Map<String, Rules> rulesMap = new ConcurrentHashMap<>();

    public static Rules getRules(String ruleName) {
        return rulesMap.get(ruleName);
    }
}
