package com.connectis.codingtask.rest;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping(TradeDataRestController.TRADE_DATA_MAPPING)
@RequiredArgsConstructor
public class TradeDataRestController {
    protected static final String TRADE_DATA_MAPPING = "/api/v1/trade-data";

    @PostMapping
    @ResponseStatus(ACCEPTED)
    public ResponseEntity<?> validateTradeData(@Valid @RequestBody TradeDataDTO tradeDataDTO) {

        return new ResponseEntity(ACCEPTED);
    }
}
