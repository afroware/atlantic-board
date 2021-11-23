package com.afroware.blockchain.wallet.rest.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Data;

@Data
public class CreateWalletDto {

    @Schema(example = "123")
    @NotBlank
    private String password;

    @Schema(example = "10000000000000000000")
    @NotNull
    private BigInteger initialBalance;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the initialBalance
	 */
	public BigInteger getInitialBalance() {
		return initialBalance;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param initialBalance the initialBalance to set
	 */
	public void setInitialBalance(BigInteger initialBalance) {
		this.initialBalance = initialBalance;
	}
}
