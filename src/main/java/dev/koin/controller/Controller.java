/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.koin.controller;

import dev.koin.request.Request;
import dev.koin.transaction.Transaction;

/**
 *
 * @author Terrapin
 */
public class Controller {

    public static void main(String[] args) {
        Request request = new Request();
        Transaction transaction = new Transaction();

        request.requestVersion();
//        transaction.sendEther();
        System.out.println(request.getKoinSymbol());
    }
}
