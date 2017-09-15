/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.koin.controller;

import dev.koin.request.RequestService;
import dev.koin.transaction.Transaction;

/**
 *
 * @author Terrapin
 */
public class Controller {

    public static void main(String[] args) {
        RequestService request = new RequestService();
        Transaction transaction = new Transaction();

        request.requestVersion();
//        transaction.sendEther();
        System.out.println(request.getKoinSymbol());
        System.out.println(request.checkIfValidAddress("0x0146E80a7f3fEE9c789a779Fac835BDA983eA2C8"));
        System.out.println(request.connectToEthereumWallet("0x0146E80a7f3fEE9c789a779Fac835BDA983eA2C8", "Khalifa2017"));
    }
}
