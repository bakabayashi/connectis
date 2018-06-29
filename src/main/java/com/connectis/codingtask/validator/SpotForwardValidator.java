package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;

import java.util.Set;

import static com.connectis.codingtask.validator.ErrorMessageUtils.SPOT_FORWARD_DATE_ERROR_MESSAGE;
import static com.connectis.codingtask.validator.ErrorMessageUtils.buildMessage;
import static com.google.common.collect.Sets.newHashSet;

public class SpotForwardValidator {

    public static Set<String> validate(TradeDataDTO tradeDataDTO) {
        Set<String> errors = newHashSet();

        if(!isTradeDateMinimum2DaysBeforeValueDate(tradeDataDTO)) {
            errors.add(buildMessage(SPOT_FORWARD_DATE_ERROR_MESSAGE, tradeDataDTO.getTradeDate(), tradeDataDTO.getValueDate()));
        }

        return errors;
    }

    private static boolean isTradeDateMinimum2DaysBeforeValueDate(TradeDataDTO tradeDataDTO) {
        return tradeDataDTO.getTradeDate().isBefore(tradeDataDTO.getValueDate().minusDays(2))
                || tradeDataDTO.getTradeDate().isEqual(tradeDataDTO.getValueDate().minusDays(2));
    }
}
