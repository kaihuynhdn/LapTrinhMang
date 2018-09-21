/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuong2;

/**
 *
 * @author Kai
 */
public class Bai1 {

    public static void main(String[] args) throws InterruptedException {
        int n = 10;
        Thread thread1 = new Thread(new Runnable1(n));
        Thread thread2 = new Thread(new Runnable2(n));
        Thread thread3= new Thread(new Runnable3(n));
        Thread thread4 = new Thread(new Runnable4());
        
        thread1.start();
        thread1.join();
        
        thread2.start();
        thread2.join();
        
        thread3.start();
        thread3.join();
        
        thread4.start();
    }
}

class Runnable1 implements Runnable {

    public int n = 0;

    public Runnable1(int n) {
        this.n = n;
    }

    public void run() {
        System.out.println("Số lẻ nhỏ hơn n: ");
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\n");
    }
}

class Runnable2 implements Runnable {

    public int n = 0;

    public Runnable2(int n) {
        this.n = n;
    }

    public void run() {
        System.out.println("Số chẵn nhỏ hơn n: ");
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println("\n");
    }
}

class Runnable3 implements Runnable {

    public int n = 0;

    public Runnable3(int n) {
        this.n = n;
    }

    public void run() {
        System.out.println("In ra từ 1 -> n: ");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
}

class Runnable4 implements Runnable {

    public void run() {
        System.out.println("Danh sách ký tự hoa: ");
        for (int i = 65; i <= 90; i++) {
            System.out.print((char) i + " ");
        }
        System.out.println();
    }
}
