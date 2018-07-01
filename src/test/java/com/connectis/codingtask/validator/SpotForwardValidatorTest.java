package com.connectis.codingtask.validator;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static com.connectis.codingtask.validator.ErrorMessageUtils.SPOT_FORWARD_DATE_ERROR_MESSAGE;
import static com.connectis.codingtask.validator.ErrorMessageUtils.buildMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class SpotForwardValidatorTest {

    @Test
    public void shouldFailWhenTradeDateIs1DayBeforeValueDate() {
        //given
        TradeDataDTO tradeDataDTO = TradeDataDTOUtils.create();
        tradeDataDTO.setTradeDate(LocalDate.now());
        tradeDataDTO.setValueDate(LocalDate.now().plusDays(1));

        //when
        Set<String> errors = SpotForwardValidator.validate(tradeDataDTO);

        //then
        assertThat(errors).isNotEmpty();
        assertThat(errors).contains(buildMessage(SPOT_FORWARD_DATE_ERROR_MESSAGE, tradeDataDTO.getTradeDate(), tradeDataDTO.getValueDate()));
    }

}