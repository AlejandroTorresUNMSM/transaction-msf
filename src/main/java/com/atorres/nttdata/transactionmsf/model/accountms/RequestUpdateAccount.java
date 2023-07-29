package com.atorres.nttdata.transactionmsf.model.accountms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestUpdateAccount {
    private BigDecimal balance;
    private String accountId;
}
