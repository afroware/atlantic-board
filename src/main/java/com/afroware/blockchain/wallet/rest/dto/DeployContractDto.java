package com.afroware.blockchain.wallet.rest.dto;

import java.math.BigInteger;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Data;

@Data
public class DeployContractDto {

    @Schema(example = "123")
    @NotBlank
    private String password;

    @Schema(example = "/path/to/UTC...")
    @NotBlank
    private String file;

    @Schema(example = "1")
    @NotNull
    @Positive
    private BigInteger gasPrice;

    @Schema(example = "3000000")
    @NotNull
    @Positive
    private BigInteger gasLimit;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
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
