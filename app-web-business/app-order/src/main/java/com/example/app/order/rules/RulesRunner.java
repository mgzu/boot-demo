package com.example.app.order.rules;

import com.example.framework.common.util.SpringContextUtil;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author MaGuangZu
 * @since 2022-10-14
 */
@Component
public class RulesRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Rule> beans = SpringContextUtil.applicationContext.getBeansOfType(Rule.class);
        // 根据 ruleName 分组
        beans.forEach((beanName, bean) -> GlobeRules.rulesMap.compute(bean.getName(), (key, rules) -> {
            if (rules == null) {
                rules = new Rules();
            }
            rules.register(bean);
            return rules;
        }));
    }

}
