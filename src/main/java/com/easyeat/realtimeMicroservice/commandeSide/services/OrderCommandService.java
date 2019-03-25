package com.easyeat.realtimeMicroservice.commandeSide.services;

import com.easyeat.realtimeMicroservice.commandeSide.commands.CancelOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.OpenOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.PayOrderCommand;
import com.easyeat.realtimeMicroservice.commandeSide.commands.ReadyOrderCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.callbacks.FutureCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@AllArgsConstructor
@Component
public class OrderCommandService {

    @Autowired
    private final CommandGateway commandGateway;


    public Future<?> remove(String id){
        FutureCallback<CancelOrderCommand, Object> callback = new FutureCallback<>();
        commandGateway.send(new CancelOrderCommand(id),callback);
        return callback.toCompletableFuture();
    }

    public Future<?> save(String id, String customerId){
        FutureCallback<OpenOrderCommand, Object> callback = new FutureCallback<>();
        commandGateway.send(new OpenOrderCommand(id,customerId), callback);
        return callback.toCompletableFuture();
    }


    public Future<?> makeOrderReady(String id){
        FutureCallback<ReadyOrderCommand, Object> callback = new FutureCallback<>();
        commandGateway.send(new ReadyOrderCommand(id),callback);
        return callback.toCompletableFuture();
    }

    public Future<?> makeOrderPaid(String id){
        FutureCallback<PayOrderCommand, Object> callback = new FutureCallback<>();
        commandGateway.send(new PayOrderCommand(id),callback);
        return callback.toCompletableFuture();
    }

}
