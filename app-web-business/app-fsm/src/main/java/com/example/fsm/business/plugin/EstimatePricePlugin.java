package com.example.fsm.business.plugin;

import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.ProcessorPlugin;
import com.example.fsm.business.context.CreateOrderContext;
import com.example.fsm.business.enums.BizCodeEnum;
import com.example.fsm.business.enums.OrderEventEnum;
import com.example.fsm.business.enums.OrderStateEnum;
import com.example.fsm.business.enums.SceneIdEnum;
import com.example.fsm.context.StateContext;
import com.example.fsm.exception.FsmException;
import com.example.fsm.plugin.PluginHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * 预估价插件
 */
@Slf4j
@ProcessorPlugin(
        state = OrderStateEnum.INIT,
        event = OrderEventEnum.CREATE,
        bizCode = {
                BizCodeEnum.FBA,
                BizCodeEnum.CARGO,
        },
        sceneId = SceneIdEnum.H5
)
public class EstimatePricePlugin implements PluginHandler<Void, CreateOrderContext> {
    @NotNull
    @Override
    public ServiceResult<Void> action(@NotNull StateContext<CreateOrderContext> context) throws FsmException {
        log.info("EstimatePricePlugin action");
        String price = "";
        context.getContext().setEstimatePriceInfo(price);
        return new ServiceResult<>(true);
    }
}
