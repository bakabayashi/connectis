package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;

import java.util.HashSet;
import java.util.Set;

import static com.connectis.codingtask.validator.ErrorMessageUtils.*;

public class OptionValidator {

    private static final String AMERICAN = "american";
    private static final String EUROPEAN = "european";

    public static Set<String> validate(TradeDataDTO tradeDataDTO) {
        Set<String> errors = new HashSet<>();

        if(!isEuropeanOrAmerican(tradeDataDTO)) {
            errors.add(buildMessage(OPTION_STYLE_ERROR_MESSAGE));
        }

        if(!isAmericanStyleWithCorrectDates(tradeDataDTO)) {
            errors.add(buildMessage(AMERICAN_STYLE_ERROR_MESSAGE, tradeDataDTO.getExcerciseStartDate(),
                    tradeDataDTO.getTradeDate(), tradeDataDTO.getExpiryDate()));
        }

        if(!isPremiumDateAndExpiryDateBeforeDelivery(tradeDataDTO)) {
            errors.add(buildMessage(EXPIRY_PREMIUM_DELIVERY_DATES_ERROR_MESSAGE, tradeDataDTO.getExpiryDate(),
                    tradeDataDTO.getPremiumDate(), tradeDataDTO.getDeliveryDate()));
        }

        return errors;
    }

    private static boolean isPremiumDateAndExpiryDateBeforeDelivery(TradeDataDTO tradeDataDTO) {
        return tradeDataDTO.getExpiryDate()!=null && tradeDataDTO.getPremiumDate()!=null && tradeDataDTO.getDeliveryDate()!=null &&
                tradeDataDTO.getExpiryDate().isBefore(tradeDataDTO.getDeliveryDate()) && tradeDataDTO.getPremiumDate().isBefore(tradeDataDTO.getDeliveryDate());
    }

    private static boolean isEuropeanOrAmerican(TradeDataDTO tradeDataDTO) {
        return tradeDataDTO!=null && tradeDataDTO.getStyle().equalsIgnoreCase(AMERICAN) || tradeDataDTO.getStyle().equalsIgnoreCase(EUROPEAN);
    }

    private static boolean isAmericanStyleWithCorrectDates(TradeDataDTO tradeDataDTO) {
        return tradeDataDTO.getStyle().equalsIgnoreCase(AMERICAN)
                && tradeDataDTO.getExcerciseStartDate()!=null
                && tradeDataDTO.getExcerciseStartDate().isAfter(tradeDataDTO.getTradeDate())
                && tradeDataDTO.getExcerciseStartDate().isBefore(tradeDataDTO.getExpiryDate());
    }
}
