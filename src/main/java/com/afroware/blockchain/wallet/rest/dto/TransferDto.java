package com.afroware.blockchain.wallet.rest.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Data;

@Data
public class TransferDto {

    @Schema(example = "0x...")
    @NotBlank
    private String fromAddress;

    @Schema(example = "0x...")
    @NotBlank
    private String toAddress;

    @Schema(example = "10000000000000000000")
    @NotNull
    @Positive
    private BigInteger amount;

    @Schema(example = "20000000000")
    @NotNull
    @Positive
    private BigInteger gasPrice;

    @Schema(example = "21000")
    @NotNull
    @Positive
    private BigInteger gasLimit;

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @return the amount
	 */
	public BigInteger getAmount() {
		return amount;
	}

	/**
	 * @return the gasPrice
	 */
	public BigInteger getGasPrice() {
		return gasPrice;
	}

	/**
	 * @return the gasLimit
	 */
	public BigInteger getGasLimit() {
		return gasLimit;
	}

	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	/**
	 * @param gasPrice the gasPrice to set
	 */
	public void setGasPrice(BigInteger gasPrice) {
		this.gasPrice = gasPrice;
	}

	/**
	 * @param gasLimit the gasLimit to set
	 */
	public void setGasLimit(BigInteger gasLimit) {
		this.gasLimit = gasLimit;
	}
}
