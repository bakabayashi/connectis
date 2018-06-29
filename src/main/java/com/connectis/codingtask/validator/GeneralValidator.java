package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.CounterParty;
import com.connectis.codingtask.model.dto.TradeDataDTO;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.jdk8.LocalDateKitCalculatorsFactory;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;
import java.util.stream.Collectors;

import static com.connectis.codingtask.validator.ErrorMessageUtils.*;
import static com.google.common.collect.Sets.newHashSet;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;


public class GeneralValidator {

    private static final String SWISS_CALENDAR = "CH";
    private static final Set<String> currencies = Currency.getAvailableCurrencies()
            .stream()
            .map(Currency::getCurrencyCode)
            .collect(Collectors.toSet());

    public static Set<String> validate(TradeDataDTO tradeDataDTO) {
        Set<String> errors = newHashSet();

        if (!isValueDateAfterTradeDate(tradeDataDTO)) {
            errors.add(buildMessage(VALUE_DATE_ERROR_MESSAGE, tradeDataDTO.getValueDate(), tradeDataDTO.getTradeDate()));
        }

        if (isWeekendOrHoliday(tradeDataDTO.getValueDate())) {
            errors.add(buildMessage(HOLIDAY_ERROR_MESSAGE, tradeDataDTO.getValueDate(), tradeDataDTO.getCcyPair()));
        }

        if (!areCounterPartiesSupported(tradeDataDTO.getCustomer())) {
            errors.add(buildMessage(SUPPORTED_COUNTERPARTIES_ERROR_MESSAGE, tradeDataDTO.getValueDate(), CounterParty.values()));
        }

        if (!isCurrencyPairSupported(tradeDataDTO)) {
            errors.add(buildMessage(SUPPORTED_CURENCY_PAIR_ERROR_MESSAGE, tradeDataDTO.getValueDate()));
        }

        return errors;
    }

    private static boolean isCurrencyPairSupported(TradeDataDTO tradeDataDTO) {
        String cur1 = tradeDataDTO.getCcyPair().substring(0, 3);
        String cur2 = tradeDataDTO.getCcyPair().substring(3);
        return currencies.contains(cur1) && currencies.contains(cur2);
    }

    private static boolean isValueDateAfterTradeDate(TradeDataDTO tradeDataDTO) {
        return tradeDataDTO.getValueDate().isBefore(tradeDataDTO.getTradeDate());
    }

    private static boolean isWeekendOrHoliday(LocalDate localDate) {
        DateCalculator<LocalDate> dateCalculator = LocalDateKitCalculatorsFactory.forwardCalculator(SWISS_CALENDAR);
        return dateCalculator.getHolidayCalendar().isHoliday(localDate)
                || localDate.getDayOfWeek() == SATURDAY
                || localDate.getDayOfWeek() == SUNDAY;
    }

    private static boolean areCounterPartiesSupported(String customer) {
        return newHashSet(CounterParty.values())
                .stream()
                .map(Enum::toString)
                .collect(Collectors.toSet())
                .contains(customer);
    }
}
