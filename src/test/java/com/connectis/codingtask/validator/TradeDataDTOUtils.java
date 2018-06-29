package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import com.connectis.codingtask.model.dto.TradeType;

import java.time.LocalDate;

public class TradeDataDTOUtils {
    static TradeDataDTO create() {
        TradeDataDTO tradeDataDTO = new TradeDataDTO();

        tradeDataDTO.setTradeDate(LocalDate.now());
        tradeDataDTO.setValueDate(LocalDate.now());
        tradeDataDTO.setAmount1(1000000.00);
        tradeDataDTO.setAmount2(1200000.00);
        tradeDataDTO.setCcyPair("EURUSD");
        tradeDataDTO.setCustomer("PLUTO1");
        tradeDataDTO.setDirection("BUY");
        tradeDataDTO.setLegalEntity("CS Zurich");
        tradeDataDTO.setRate(1.12);
        tradeDataDTO.setTrader("Johann Baumfiddler");
        tradeDataDTO.setType(TradeType.FORWARD);

        return tradeDataDTO;
    }
}
