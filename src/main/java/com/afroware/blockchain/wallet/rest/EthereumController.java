package  com.afroware.blockchain.wallet.rest;

import java.io.IOException;
import java.math.BigInteger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import com.afroware.blockchain.message.Message;
import com.afroware.blockchain.message.MessageType;
import com.afroware.blockchain.wallet.rest.dto.CreateWalletDto;
import com.afroware.blockchain.wallet.rest.dto.DeployContractDto;
import com.afroware.blockchain.wallet.rest.dto.GetWalletAddressDto;
import com.afroware.blockchain.wallet.rest.dto.TransferDto;
import com.afroware.blockchain.wallet.rest.dto.WalletDto;
import com.afroware.blockchain.wallet.service.EthereumService;


@RequestScoped
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class EthereumController {
	
	@Inject
    private  EthereumService ethereumService ;

    @GET
    @Path("/accounts")
    public Response getAccounts() throws IOException {
    	Message message = new Message();
		message.setData(ethereumService.getAccounts());
		message.setMessageType(MessageType.HELLO);
        return Response.ok( message).build();
    }
    
    @GET
    @Path("/account/{address}")
    public Response getAccount(@PathParam("address") String address) throws IOException {
    	Message message = new Message();
		message.setData(ethereumService.getAccount(address));
		message.setMessageType(MessageType.HELLO);
        return Response.ok( message).build();
    }

    @POST
    @Path("/wallets/create")
    public WalletDto createWallet(@Valid @RequestBody CreateWalletDto createWalletDto) throws Exception {
        String file = ethereumService.createWallet(createWalletDto.getPassword());
        String address = ethereumService.getWallet(createWalletDto.getPassword(), file).getAddress();

        if (createWalletDto.getInitialBalance().signum() == 1) {
            String account0Address = ethereumService.getAccounts().get(0);
            ethereumService.transfer(account0Address, address, createWalletDto.getInitialBalance(),
                    BigInteger.valueOf(20000000000L), BigInteger.valueOf(21000L));
        }

        return new WalletDto(file, address);
    }

    @POST
    @Path("/wallets/get")
    public Credentials getWallet(@Valid @RequestBody GetWalletAddressDto getWalletAddressDto) throws IOException, CipherException {
        return ethereumService.getWallet(getWalletAddressDto.getPassword(), getWalletAddressDto.getFile());
    }

    @GET
    @Path("/wallets/{address}/balance")
    public BigInteger getWalletBalance(@PathParam(value = "address") String address) throws IOException {
        return ethereumService.getWalletBalance(address);
    }

    @POST
    @Path("/wallets/transfer")
    public EthSendTransaction transfer(@Valid @RequestBody TransferDto transferDto) throws IOException {
        return ethereumService.transfer(
                transferDto.getFromAddress(),
                transferDto.getToAddress(),
                transferDto.getAmount(),
                transferDto.getGasPrice(),
                transferDto.getGasLimit());
    }

    @POST
    @Path("/contracts/deploy/soccerManager")
    public String deploySoccerManagerContract(@Valid @RequestBody DeployContractDto deployContractDto) throws Exception {
        return ethereumService.deploySoccerManagerContract(
                deployContractDto.getPassword(),
                deployContractDto.getFile(),
                deployContractDto.getGasPrice(),
                deployContractDto.getGasLimit());
    }
}
