/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.koin.request;

import dev.koin.KoinToken_sol_KoinToken;
import dev.koin.transaction.TransactionService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.*;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

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
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(TransactionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return symbolAsString;
    }

    public boolean createWalletFile(String password) {
        boolean flag = false;
        Path p1 = Paths.get("src/main/resources");

        return flag;
    }

    public boolean checkIfValidAddress(String address) {
        if (WalletUtils.isValidAddress(address)) {
            return true;
        }
        return false;
    }
    
    public String getFileName(Part part) {
        String partHeader = part.getHeader("content-disposition");
//        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public void addFileToKeystore(PrintWriter writer, String path, String fileName, Part filePart) {
        OutputStream out = null;
        InputStream fileContent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            fileContent = filePart.getInputStream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + fileName + " created at " + path);
//            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
//                    new Object[]{fileName, path});
        } catch (Exception e) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + e.getMessage());

//            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
//                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (fileContent != null) {
                try {
                    fileContent.close();
                } catch (IOException ex) {
                    Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    public Credentials connectToEthereumWallet(String password, String pathToWalletFile, String walletFileName) {
        try {
            this.credentials = WalletUtils.loadCredentials(password, pathToWalletFile + "/" + walletFileName);
            System.out.println("Credentials loaded...");
            return credentials;
        } catch (IOException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CipherException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return credentials;
    }
    
    public Uint256 getBalance(String address) {
        Uint256 balance = null;
        try {
            balance = koinToken.balanceOf(new Address(address)).get();
        } catch (InterruptedException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(RequestService.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Uint256 balance = koinToken.balanceOf(address);
        return balance;
    }
    
    public boolean transferKoin(BigDecimal koin) {
        String userAddress = credentials.getAddress();
        koinToken.transferFrom(new Address("0x0146e80a7f3fee9c789a779fac835bda983ea2c8"), new Address(userAddress), new Uint256(koin.toBigInteger()));
        return true;
    }
}
