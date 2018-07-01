package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static com.connectis.codingtask.validator.ErrorMessageUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GeneralValidatorTest {

    @Test
    public void shouldProduceErrorForWrongLegalEntity() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();
        tradeDataDTO.setLegalEntity("Hamburg");

        //when
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).hasSize(1);
        assertThat(errors).contains(LEGAL_ENTITY_ERROR_MESSAGE);
    }

    @Test
    public void shouldProduceErrorForValueDateBeforeTradeDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();
        tradeDataDTO.setValueDate(tradeDataDTO.getTradeDate().minusDays(1));

        //when
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).hasSize(1);
        assertThat(errors).contains(buildMessage(VALUE_DATE_ERROR_MESSAGE, tradeDataDTO.getValueDate(), tradeDataDTO.getTradeDate()));
    }

    @Test
    public void shouldProduceErrorForTradeDateOnWeekend() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();
        tradeDataDTO.setValueDate(LocalDate.parse("2018-06-30"));

        //when
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).hasSize(1);
        assertThat(errors).contains(buildMessage(HOLIDAY_ERROR_MESSAGE, tradeDataDTO.getValueDate(), tradeDataDTO.getCcyPair()));
    }

    @Test
    public void shouldProduceErrorForUnsupportedCounterParty() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();
        tradeDataDTO.setCustomer("PLUTO3");

        //when
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).hasSize(1);
        assertThat(errors).contains(buildMessage(SUPPORTED_COUNTERPARTIES_ERROR_MESSAGE, tradeDataDTO.getCustomer()));
    }

    @Test
    public void shouldProduceErrorForUnsupportedCurrency() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();
        tradeDataDTO.setCcyPair("USWPLX");

        //when
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).hasSize(1);
        assertThat(errors).contains(buildMessage(SUPPORTED_CURRENCY_PAIR_ERROR_MESSAGE, tradeDataDTO.getCcyPair()));
    }

    @Test
    public void shouldNotProduceError() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();

        //when
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isEmpty();
    }
}