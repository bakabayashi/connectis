package com.connectis.codingtask.service;

import com.connectis.codingtask.model.dto.TradeDataDTO;
import com.connectis.codingtask.model.exception.TradeDataValidationException;
import com.connectis.codingtask.validator.GeneralValidator;
import com.connectis.codingtask.validator.OptionValidator;
import com.connectis.codingtask.validator.SpotForwardValidator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TradeDataValidationService {

    public void validate(TradeDataDTO tradeDataDTO) {
        Set<String> errors = GeneralValidator.validate(tradeDataDTO);

        switch(tradeDataDTO.getType()) {
            case Options:
                errors.addAll(OptionValidator.validate(tradeDataDTO));
                break;
            case Spot:
            case Forward:
                errors.addAll(SpotForwardValidator.validate(tradeDataDTO));
                break;
        }

        if(!errors.isEmpty()) {
            throw new TradeDataValidationException(errors);
        }
    }
}
