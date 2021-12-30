package com.example.business.plugin;


import com.example.business.context.CreateOrderContext;
import com.example.fsm.ServiceResult;
import com.example.fsm.annotation.ProcessorPlugin;
import com.example.fsm.context.StateContext;
import com.example.fsm.enums.OrderEventEnum;
import com.example.fsm.enums.OrderStateEnum;
import com.example.fsm.plugin.PluginHandler;

/**
 * 预估价插件
 */
@ProcessorPlugin(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = "BUSINESS")
public class EstimatePricePlugin implements PluginHandler<String, CreateOrderContext> {
    @Override
    public ServiceResult action(StateContext<CreateOrderContext> context) throws Exception {
//        String price = priceSerive.getPrice();
        String price = "";
        context.getContext().setEstimatePriceInfo(price);
        return new ServiceResult();
    }
}
