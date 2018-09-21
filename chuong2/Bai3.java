/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuong2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kai
 */
class Account {

    private double balance;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    
    public double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        double money = this.balance + amount;
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBalance(money);
        System.out.println("Success! You have transferred $"
                + amount + ". Your balance: " + getBalance());
    }

    public synchronized void withdraw(double amount) {
        if (balance - amount >= 0) {
            double money = this.balance - amount;
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
            setBalance(money);
            System.out.println("Success! You have withdrawn $"
                + amount + ". Your balance: " + getBalance());
        } else {
            System.out.println("You dont have enought money.");
        }
    }
}

class AccountTest{
    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(10);
        
        System.out.println("Balance:" + account.getBalance());
        
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                    account.deposit(2);
            }
        });
        a.start();
        
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(5);
            }
        });
        b.start();
        
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                account.withdraw(7);
            }
        });
        c.start();
        
        a.join();
        b.join();
        c.join();
        
        System.out.println(account.getBalance());
    }
}
