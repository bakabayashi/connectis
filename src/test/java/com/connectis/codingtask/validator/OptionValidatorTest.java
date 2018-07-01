package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static com.connectis.codingtask.validator.ErrorMessageUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OptionValidatorTest {

    @Test
    public void shouldFailWhenNotAmericanNorEuropean() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();
        tradeDataDTO.setStyle("AFRICAN");

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).contains(buildMessage(OPTION_STYLE_ERROR_MESSAGE));
    }

    @Test
    public void shouldFailWhenAmericanAndExerciseDateIsNotAfterTradeDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();
        tradeDataDTO.setStyle("AMERICAN");
        tradeDataDTO.setExcerciseStartDate(LocalDate.now());
        tradeDataDTO.setTradeDate(LocalDate.now());
        tradeDataDTO.setExpiryDate(LocalDate.now().plusDays(1));

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).contains(buildMessage(AMERICAN_STYLE_ERROR_MESSAGE, tradeDataDTO.getExcerciseStartDate(),
                tradeDataDTO.getTradeDate(), tradeDataDTO.getExpiryDate()));
    }

    @Test
    public void shouldFailWhenAmericanAndExerciseDateIsNotAfterExpiryDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();
        tradeDataDTO.setStyle("AMERICAN");
        tradeDataDTO.setExcerciseStartDate(LocalDate.now());
        tradeDataDTO.setTradeDate(LocalDate.now().minusDays(1));
        tradeDataDTO.setExpiryDate(LocalDate.now());

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).contains(buildMessage(AMERICAN_STYLE_ERROR_MESSAGE, tradeDataDTO.getExcerciseStartDate(),
                tradeDataDTO.getTradeDate(), tradeDataDTO.getExpiryDate()));
    }

    @Test
    public void shouldNotFailWhenAmericanAndExerciseDateIsAfterExpiryDateAndBeforeTradeDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();
        tradeDataDTO.setStyle("AMERICAN");
        tradeDataDTO.setExcerciseStartDate(LocalDate.now());
        tradeDataDTO.setTradeDate(LocalDate.now().minusDays(1));
        tradeDataDTO.setExpiryDate(LocalDate.now().plusDays(1));

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isEmpty();
    }

    @Test
    public void shouldFailWhenPremiumDateNotBeforeDeliveryDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();
        tradeDataDTO.setPremiumDate(LocalDate.now());
        tradeDataDTO.setDeliveryDate(LocalDate.now());

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).contains(buildMessage(EXPIRY_PREMIUM_DELIVERY_DATES_ERROR_MESSAGE, tradeDataDTO.getExpiryDate(),
                tradeDataDTO.getPremiumDate(), tradeDataDTO.getDeliveryDate()));

    }

    @Test
    public void shouldFailWhenExpiryDateNotBeforeDeliveryDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();
        tradeDataDTO.setExpiryDate(LocalDate.now());
        tradeDataDTO.setDeliveryDate(LocalDate.now());

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).contains(buildMessage(EXPIRY_PREMIUM_DELIVERY_DATES_ERROR_MESSAGE, tradeDataDTO.getExpiryDate(),
                tradeDataDTO.getPremiumDate(), tradeDataDTO.getDeliveryDate()));

    }

    @Test
    public void shouldNotFailWhenExpiryAndPremiumDatesBeforeDeliveryDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.createOption();

        //when
        Set<String> errors = OptionValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isEmpty();

    }

}