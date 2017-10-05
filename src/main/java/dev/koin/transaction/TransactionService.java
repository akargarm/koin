/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.koin.transaction;

import dev.koin.KoinToken_sol_KoinToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

/**
 *
 * @author Terrapin
 */
public class TransactionService {

    Web3j web3 = Web3j.build(new HttpService());
    Credentials credentials;

    public TransactionService() {
        try {
            this.credentials = WalletUtils.loadCredentials("test", "src/main/resources/UTC--2017-09-05T23-58-08.153000000Z--0146e80a7f3fee9c789a779fac835bda983ea2c8.json");
//            this.credentials = WalletUtils.loadCredentials("test", "/Users/akargarm/Documents/NetBeans_Projects/koin/src/main/resources/UTC--2017-09-05T23-58-08.153000000Z--0146e80a7f3fee9c789a779fac835bda983ea2c8.json");
        } catch (IOException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CipherException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendEther() {
        TransactionReceipt transactionReceipt;
        try {
            System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
            transactionReceipt = Transfer.sendFundsAsync(web3, credentials, "0x308bb6a6598580bf340d31521d3ebea959bf109f", BigDecimal.valueOf(0.2), Convert.Unit.ETHER).get();
            System.out.println("Funds transfer completed: " + transactionReceipt.getBlockHash());
        } catch (Exception e) {
            System.err.println("Failure");
        }
    }

}
