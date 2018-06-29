package com.connectis.codingtask.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class TradeDataDTO {

    @NotNull
    private String customer;

    @NotNull
    @Min(value = 6)
    @Max(value = 6)
    private String ccyPair;

    @NotNull
    private TradeType type;

    @NotNull
    private String direction;

    @NotNull
    private LocalDate tradeDate;

    @NotNull
    private Double amount1;

    @NotNull
    private Double amount2;

    @NotNull
    private Double rate;

    @NotNull
    private LocalDate valueDate;

    @NotNull
    private String legalEntity;

    @NotNull
    private String trader;

}
