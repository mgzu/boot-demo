package com.example.fsm.plugin;

import com.example.fsm.context.StateContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MaGuangZu
 * @since 2021-09-26
 */
@Component
public class PluginExecutor {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public <C> void parallelExecutor(StateContext<C> context) {

    }
}
