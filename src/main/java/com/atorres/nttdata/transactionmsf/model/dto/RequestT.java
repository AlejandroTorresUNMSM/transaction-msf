package com.atorres.nttdata.transactionmsf.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestT {
	private BigDecimal amount;
	private String clientId;
}
