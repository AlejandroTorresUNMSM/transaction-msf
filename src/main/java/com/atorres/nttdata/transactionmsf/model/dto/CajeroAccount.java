package com.atorres.nttdata.transactionmsf.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CajeroAccount extends RequestT{
	private String account;
}
