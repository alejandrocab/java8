package com.codechallenge.application.util;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.codechallenge.application.exceptions.AmountAccountNegativeException;

@Component
public class CalculateAmountComponent {

	public Double calculateTransactionAmount (Double amount, Double fee) {
		BigDecimal bdAmount = BigDecimal.valueOf(amount);
		BigDecimal bdFee = BigDecimal.valueOf(fee);
		return bdAmount.subtract(bdFee).doubleValue();
	}
	
	public Double calculateAccountAmount (Double transactionAmount, Double accountAmount) {
		BigDecimal bdTransaction = BigDecimal.valueOf(transactionAmount);
		BigDecimal bdAccount = BigDecimal.valueOf(accountAmount);
		BigDecimal amountAccountCalculated = bdAccount.add(bdTransaction);
		if (BigDecimal.ZERO.compareTo(amountAccountCalculated) == 1) {
			throw new AmountAccountNegativeException();
		}
		return amountAccountCalculated.doubleValue();
	}
}
