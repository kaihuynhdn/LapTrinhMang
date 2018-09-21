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
public class Bai5 {

    public static void main(String[] args) {

        Resource resource = new Resource();

        Thread t1 = new Thread(new FindPrime(resource));
        Thread t2 = new Thread(new SumPrimes(resource));

        t1.start();
        t2.start();
    }
}

class Resource {

    private int value = -1;

    private boolean isFinded = false;

    private boolean isTerminate = false;

    public synchronized void setPrimeNumber(int n) throws InterruptedException {
        while (isFinded) {
            System.out.println("\nLUỒNG TÌM SỐ NGUYÊN TỐ đang chờ LUỒNG TỔNG\n");
            wait();
        }
        System.out.println("Prime number: " + n);
        value = n;
        isFinded = true;
    }

    public synchronized int getPrimeNumber() {

        while (!isFinded) {
            try {
                System.out.println("\nLUỒNG TỔNG đang chờ LUỒNG TÌM SỐ NGUYÊN TỐ\n");
                wait();
            } catch (InterruptedException ex) {
                
            }
        }
        isFinded = false;
        return value;
    }

    public boolean isTerminate() {
        return isTerminate;
    }

    public synchronized void setTerminate(boolean isTerminate) throws InterruptedException {
        while (isFinded) {
            wait();
        }
        System.out.println("done");
        this.isTerminate = isTerminate;
        value = 0;
        isFinded = true;
    }

    public synchronized void notifyThread() {
        notifyAll();
    }

}

class FindPrime implements Runnable {

    private Resource resource;

    public FindPrime(Resource r) {
        this.resource = r;
    }

    @Override
    public void run() {
        try {
            for (int i = 100; i <= 1000; i++) {
                if (isPrime(i)) {
                    resource.setPrimeNumber(i);
                    resource.notifyThread();
                }
            }

            resource.setTerminate(true);
            resource.notifyThread();

        } catch (InterruptedException ex) {
            
        }
    }

    public boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}

class SumPrimes implements Runnable {

    private Resource resource;
    private int sum;

    public SumPrimes(Resource resource) {
        this.resource = resource;

        sum = 0;
    }

    @Override
    public void run() {
        while (true) {
            if (!resource.isTerminate()) {
                sum += resource.getPrimeNumber();
                System.out.println("Sum: " + sum);
                resource.notifyThread();
            } else {
                break;
            }
        }
    }
}
