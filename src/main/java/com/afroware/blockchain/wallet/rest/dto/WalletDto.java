package com.afroware.blockchain.wallet.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WalletDto {

	private String file;
	private String address;

	/**
	 * @param file
	 * @param address
	 */
	public WalletDto(String file, String address) {
		this.file = file;
		this.address = address;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
