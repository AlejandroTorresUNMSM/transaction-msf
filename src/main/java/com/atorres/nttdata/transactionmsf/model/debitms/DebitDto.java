package com.atorres.nttdata.transactionmsf.model.debitms;

import lombok.Data;

import java.util.List;

@Data
public class DebitDto {
	private String id;
	private String client;
	private String mainProduct;
	private List<String> productList;
}
