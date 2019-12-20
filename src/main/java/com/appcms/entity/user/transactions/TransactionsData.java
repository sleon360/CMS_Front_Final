package com.appcms.entity.user.transactions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionsData {
	
	@JsonProperty("transactions")
	List<Transaction> transactions;
	
	@JsonProperty("next_cursor")
	String nextCursor;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getNextCursor() {
		return nextCursor;
	}

	public void setNextCursor(String nextCursor) {
		this.nextCursor = nextCursor;
	}
	
}