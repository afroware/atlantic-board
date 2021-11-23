package com.afroware.blockchain.wallet.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import com.afroware.blockchain.model.SoccerManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
public class EthereumServiceImpl implements EthereumService {

	private final File walletDirectory;
	
	private final Web3j web3j;
	
	@ConfigProperty(name = "mainnet.url")
	private final String MAINNET_URL = "http://192.168.11.101:7545" ; 


	public EthereumServiceImpl() {
		this.web3j = new JsonRpc2_0Web3j(new HttpService(MAINNET_URL));
		walletDirectory = new File(System.getProperty("user.dir"));
		System.err.println("Wallet Directory: {}" + walletDirectory);
	}

	public List<String> getAccounts() throws IOException {
		EthAccounts ethAccounts = web3j.ethAccounts().send();
		return ethAccounts.getAccounts();
	}

	public String createWallet(String password) throws Exception {
		String walletFile = WalletUtils.generateLightNewWalletFile(password, walletDirectory);
		return String.format("%s%s%s", walletDirectory.getAbsolutePath(), File.separator, walletFile);
	}

	public Credentials getWallet(String password, String file) throws IOException, CipherException {
		return WalletUtils.loadCredentials(password, file);
	}

	public BigInteger getWalletBalance(String address) throws IOException {
		EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
		return ethGetBalance.getBalance();
	}

	public EthSendTransaction transfer(String fromAddress, String toAddress, BigInteger amount, BigInteger gasPrice,
			BigInteger gasLimit) throws IOException {
		EthCoinbase coinbase = web3j.ethCoinbase().send();

		EthGetTransactionCount transactionCount = web3j
				.ethGetTransactionCount(coinbase.getAddress(), DefaultBlockParameterName.LATEST).send();

		Transaction transaction = Transaction.createEtherTransaction(fromAddress,
				transactionCount.getTransactionCount(), gasPrice, gasLimit, toAddress, amount);

		return web3j.ethSendTransaction(transaction).send();
	}

	public String deploySoccerManagerContract(String password, String file, BigInteger gasPrice, BigInteger gasLimit)
			throws Exception {
		Credentials credentials = WalletUtils.loadCredentials(password, file);

		ContractGasProvider gasContractProvider = new StaticGasProvider(gasPrice, gasLimit);
		SoccerManager soccerManager = SoccerManager.deploy(web3j, credentials, gasContractProvider).send();

		return soccerManager.getContractAddress();
	}

	public String getAccount(String address) throws IOException {
		EthAccounts ethAccounts = web3j.ethAccounts().send();
		return ethAccounts.getAccounts().stream().filter(ac -> ac.equals(address)).collect(Collectors.toList()).get(0);
	}
}
