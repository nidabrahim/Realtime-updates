package com.easyeat.realtimeMicroservice.commandeSide.commands;

import com.easyeat.realtimeMicroservice.api.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpenOrderCommand {
    @TargetAggregateIdentifier
    String id;
    String customerId;
    List<ProductDTO> products;


}
