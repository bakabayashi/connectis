package com.connectis.codingtask.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    private BigDecimal amount1;

    @NotNull
    private BigDecimal amount2;

    @NotNull
    private BigDecimal rate;

    @NotNull
    private LocalDate valueDate;

    @NotNull
    private String legalEntity;

    @NotNull
    private String trader;

    private String style;

    private String strategy;

    private String payCcy;

    private BigDecimal premium;

    private String premiumCcy;

    private String premiumType;

    private LocalDate premiumDate;

    private LocalDate deliveryDate;

    private LocalDate expiryDate;

    private LocalDate excerciseStartDate;

}
