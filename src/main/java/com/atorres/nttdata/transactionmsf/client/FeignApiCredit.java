package com.atorres.nttdata.transactionmsf.client;

import com.atorres.nttdata.transactionmsf.model.creditms.CreditDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "credit-msf", url = "${credit.ms.url}/")
public interface FeignApiCredit {
	@GetMapping( value = "client/{id}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Flux<CreditDto> getAllCreditClient(@PathVariable String id);

	@GetMapping( value = "/{creditId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	Mono<CreditDto> getCredit(@PathVariable String creditId);
}
