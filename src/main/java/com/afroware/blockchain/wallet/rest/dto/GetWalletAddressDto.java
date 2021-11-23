package com.afroware.blockchain.wallet.rest.dto;

import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Data;

@Data
public class GetWalletAddressDto {

    @Schema(example = "123")
    @NotBlank
    private String password;

    @Schema(example = "/path/to/UTC...")
    @NotBlank
    private String file;

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
}
