package com.example.demo.fsm.plugin;


import com.example.demo.fsm.context.CreateOrderContext;
import com.example.demo.fsm.ServiceResult;
import com.example.demo.fsm.context.StateContext;
import com.example.demo.fsm.annotation.ProcessorPlugin;
import com.example.demo.fsm.enums.OrderEventEnum;
import com.example.demo.fsm.enums.OrderStateEnum;

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
