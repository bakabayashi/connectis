package com.connectis.codingtask.validator;

class ErrorMessageUtils {
    static final String VALUE_DATE_ERROR_MESSAGE = "Value date (%s) cannot be before trade date (%s).";
    static final String HOLIDAY_ERROR_MESSAGE = "value date (%s) cannot fall on weekend or non-working day for currency (%s).";
    static final String SUPPORTED_COUNTERPARTIES_ERROR_MESSAGE = "Counterparty (%s) is not one of the supported ones.";
    static final String SUPPORTED_CURRENCY_PAIR_ERROR_MESSAGE = "Currency pair (%s) is not provided in valid ISO-4217 form.";
    static final String SPOT_FORWARD_DATE_ERROR_MESSAGE = "Between trade date (%s) and value date (%s) should be at list 2 days difference.";
    static final String AMERICAN_STYLE_ERROR_MESSAGE = "Exercise start date (%s) has to be after the trade date (%s) but before the expiry date (%s).";
    static final String OPTION_STYLE_ERROR_MESSAGE = "The style can be either American or European";
    static final String EXPIRY_PREMIUM_DELIVERY_DATES_ERROR_MESSAGE = "Expiry date (%s) and premium date (%s) shall be before delivery date (%s).";

    static String buildMessage(String message, Object ...parameters) {
        return String.format(message, parameters);
    }
}
