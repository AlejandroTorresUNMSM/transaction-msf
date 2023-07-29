package com.atorres.nttdata.transactionmsf.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RetiroDebit extends RequestT{
	private String debit;
}
