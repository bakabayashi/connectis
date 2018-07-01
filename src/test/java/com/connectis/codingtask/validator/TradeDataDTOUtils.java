package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import com.connectis.codingtask.model.dto.TradeType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TradeDataDTOUtils {
    static TradeDataDTO create() {
        TradeDataDTO tradeDataDTO = new TradeDataDTO();

        tradeDataDTO.setTradeDate(LocalDate.now());
        tradeDataDTO.setValueDate(LocalDate.now());
        tradeDataDTO.setAmount1(new BigDecimal(1000000.00));
        tradeDataDTO.setAmount2(new BigDecimal(1200000.00));
        tradeDataDTO.setCcyPair("EURUSD");
        tradeDataDTO.setCustomer("PLUTO1");
        tradeDataDTO.setDirection("BUY");
        tradeDataDTO.setLegalEntity("CS Zurich");
        tradeDataDTO.setRate(new BigDecimal(1.12));
        tradeDataDTO.setTrader("Johann Baumfiddler");
        tradeDataDTO.setType(TradeType.Forward);

        return tradeDataDTO;
    }

    static TradeDataDTO createOption() {
        TradeDataDTO tradeDataDTO = create();
        tradeDataDTO.setStyle("AMERICAN");
        tradeDataDTO.setExpiryDate(LocalDate.now().plusDays(1));
        tradeDataDTO.setTradeDate(LocalDate.now().minusDays(1));
        tradeDataDTO.setExcerciseStartDate(LocalDate.now());
        tradeDataDTO.setPremiumDate(LocalDate.now());
        tradeDataDTO.setDeliveryDate(LocalDate.now().plusDays(10));

        return tradeDataDTO;
    }
}
