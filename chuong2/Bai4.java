/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuong2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kai
 */
public class Bai4 {

    private static final String FILE_NAME = "PrimeNumbers.txt";

    public static void startThreads(Thread[] threads) {
        for (Thread t : threads) {
            t.start();
        }
    }
    
    public static void waitUntilThreadsFinish(Thread[] threads) {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bai4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void findPrimeNumber(FileWriter fileWriter, int a, int b) {
        try {
            for (int i = a; i <= b; i++) {
                if (isPrime(i)) {
                    fileWriter.append(i + "\n");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Bai4.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws IOException {

        FileWriter fileWriter = new FileWriter("PrimeNumbers.txt");
        int n = 3;

        Thread[] threads = new Thread[n];

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter A: ");
        int a = scanner.nextInt();
        System.out.print("Enter B: ");
        int b = scanner.nextInt();

        int dis = (b - a) / n;

        for (int i = 0; i < n; i++) {
            final int x = i;

            if (i == 0) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        findPrimeNumber(fileWriter, a, a + dis);
                    }
                });
                threads[i] = t;
            } else if (i == n - 1) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        findPrimeNumber(fileWriter, a + dis * x + 1, b);
                    }
                });
                threads[i] = t;
            } else {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        findPrimeNumber(fileWriter, a + dis * x + 1, a + dis * (x + 1));
                    }
                });
                threads[i] = t;
            }
        }

        startThreads(threads);
        waitUntilThreadsFinish(threads);
        fileWriter.close();
    }
}
