/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.koin.request;

import dev.koin.KoinToken_sol_KoinToken;
import dev.koin.transaction.Transaction;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.*;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;

/**
 *
 * @author Terrapin
 */
public class RequestService {

    Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
    Credentials credentials;
    Credentials userCredentials;
    KoinToken_sol_KoinToken koinToken;
//    public static void main(String[] args) {

    public RequestService() {
        try {
            this.credentials = WalletUtils.loadCredentials("test", "src/main/resources/UTC--2017-09-05T23-58-08.153000000Z--0146e80a7f3fee9c789a779fac835bda983ea2c8.json");
        } catch (IOException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CipherException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.koinToken = KoinToken_sol_KoinToken.load("0x13B507B9554B231eBFdAF7B99Af96890D4b58A53",
                    web3, credentials, BigInteger.ZERO, BigInteger.ZERO);
//        try {
//            this.credentials = WalletUtils.loadCredentials("test", "src/main/resources/UTC--2017-09-05T23-58-08.153000000Z--0146e80a7f3fee9c789a779fac835bda983ea2c8.json");
//        } catch (IOException ex) {
//            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (CipherException ex) {
//            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void requestVersion() {
        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("Client version: " + clientVersion);
        } catch (InterruptedException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getKoinSymbol() {
        String symbolAsString = null;
        try {
//            KoinToken_sol_KoinToken koinToken = KoinToken_sol_KoinToken.load("0x13B507B9554B231eBFdAF7B99Af96890D4b58A53",
//                    web3, credentials, BigInteger.ZERO, BigInteger.ZERO);
            Utf8String symbol = koinToken.symbol().get();
            symbolAsString = symbol.getValue();
//            return symbolAsString;
        } catch (InterruptedException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return symbolAsString;
    }
    
    public boolean createWalletFile(String password) {
        boolean flag = false;
        Path p1 = Paths.get("src/main/resources");

        return flag;
    }
    
//    //samplecommentsformac//
    
    public boolean checkIfValidAddress(String address) {
        if(WalletUtils.isValidAddress(address)) {
            return true;
        }
       return false;
    }
    public boolean connectToEthereumWallet(String walletAddress, String password) {
        boolean flag = false;
        Parity parity = Parity.build(new HttpService());
        try {
            PersonalUnlockAccount personalUnlockAccount = parity.personalUnlockAccount(walletAddress, password).sendAsync().get();
            if(personalUnlockAccount.accountUnlocked()) {
                flag = true;
                System.out.println(walletAddress);
                return flag;
            }
//        boolean flag = false;
//        try {
////            this.credentials = WalletUtils.loadCredentials(password, "src/main/resources/UTC--2017-09-05T23-58-08.153000000Z--0146e80a7f3fee9c789a779fac835bda983ea2c8.json");
//            userCredentials = WalletUtils.loadCredentials(password, walletAddress);
////            getKoinSymbol();
//            flag = true;
//            return flag;
//        } catch (IOException ex) {
//            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (CipherException ex) {
//            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
        } catch (InterruptedException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Aye Aye");
        System.out.println("Yo");
        return flag;
    }
}
