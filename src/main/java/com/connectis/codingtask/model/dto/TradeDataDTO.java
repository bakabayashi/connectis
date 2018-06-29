package com.connectis.codingtask.model.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class TradeDataDTO {

    @NotNull
    private String customer;

    @NotNull
    private String ccyPair;

    @NotNull
    private TradeType type;

    @NotNull
    private String direction;

    @NotNull
    private LocalDate tradeDate;

    @NotNull
    private Double ammount1;

    @NotNull
    private Double ammount2;

    @NotNull
    private Double rate;

    @NotNull
    private LocalDate valueDate;

    @NotNull
    private String legalEntity;

    @NotNull
    private String trader;

}
