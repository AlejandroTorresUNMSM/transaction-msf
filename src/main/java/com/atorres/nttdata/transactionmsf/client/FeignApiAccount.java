package com.atorres.nttdata.transactionmsf.client;

import com.atorres.nttdata.transactionmsf.model.accountms.AccountDto;
import com.atorres.nttdata.transactionmsf.model.accountms.RequestUpdateAccount;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(value = "account-msf", url = "${account.ms.url}")
public interface FeignApiAccount {
	@GetMapping(value = "{productId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<AccountDto> getAccount(@PathVariable String productId);

	@GetMapping(value = "client/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<AccountDto> getAllAccountClient(@PathVariable String id);

	@PutMapping(value="update",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<AccountDto> updateAccount(@RequestBody RequestUpdateAccount request);
}
