package com.atorres.nttdata.transactionmsf.utils;

import com.atorres.nttdata.transactionmsf.client.FeignApiAccount;
import com.atorres.nttdata.transactionmsf.exception.CustomException;
import com.atorres.nttdata.transactionmsf.model.TransactionDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@Log4j2
public class ComissionCalculator {
	@Autowired
	FeignApiAccount feignApiAccount;

	/**
	 * Metodo que calcula la comision y revisa que el monto no exceda
	 * @param idClient id cliente
	 * @param idAccount id cuenta
	 * @param amount monto
	 * @param listTrans lista transferencias del mes de una cuenta
	 * @return BigDecimal
	 */
	public Mono<BigDecimal> getComission(String idClient, String idAccount, BigDecimal amount,Flux<TransactionDto> listTrans) {
		return feignApiAccount.getAllAccountClient(idClient)
						.filter(account -> account.getId().equals(idAccount))
						.switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No existe la cuenta o el monto excede ")))
						.single()
						.flatMap(account -> getLimitTransaction(listTrans)
										.flatMap(value -> Boolean.TRUE.equals(value) ? Mono.just(new BigDecimal("0.0")) : Mono.just(getCommisionValue(account.getAccountCategory().toString())))
										.flatMap(value -> Mono.just(amount.multiply(value))));
	}

	/**
	 * Metodo que retorna el porcentaje de la comision
	 * @param tipo tipo
	 * @return BigDecimal
	 */
	static BigDecimal getCommisionValue(String tipo) {
		log.info("Le toca comision tipo: " + tipo);
		return ComissionEnum.getValueByKey(tipo);
	}

	/**
	 * Metodo que evalua el limite de transacciones por cuenta para cobrar una comision
	 * @param listTransaction lista de transacciones
	 * @return boolean
	 */
	static Mono<Boolean> getLimitTransaction(Flux<TransactionDto> listTransaction) {
		return listTransaction
						.count()
						.flatMap(cant -> {
							if(cant<=20){
								log.info("No excedio el limite de transacciones gratuitas cant:"+cant);
								return Mono.just(true);
							}else{
								log.info("Excedio el limite de transaccion gratuitas cant:"+cant);
								return Mono.just(false);
							}
						});
	}
}
