package com.atorres.nttdata.transactionmsf.model;

import com.atorres.nttdata.transactionmsf.model.accountms.AccountDto;
import com.atorres.nttdata.transactionmsf.model.clientms.ClientDto;
import com.atorres.nttdata.transactionmsf.model.creditms.CreditDto;
import com.atorres.nttdata.transactionmsf.model.debitms.DebitDto;
import lombok.Data;

import java.util.List;

@Data
public class ReportClient {
	private String clientId;
	private ClientDto clientDto;
	private List<CreditDto> creditDtoList;
	private List<AccountDto> accountDtoList;
	private List<DebitDto> debitDtoList;
}
