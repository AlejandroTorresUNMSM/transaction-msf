package com.atorres.nttdata.transactionmsf.model.creditms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestCredit {
    private BigDecimal balance;
}
