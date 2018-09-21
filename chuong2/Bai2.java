/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltm;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kai
 */
class Test {

    private double height;
    private double width;
    private double perimeter;
    private double area;

    public Test() {

    }

    public void inputData() {
        System.out.println(Thread.currentThread().getName() + ": ");

        Scanner sc = new Scanner(System.in);
        System.out.print("\tEnter height: ");
        this.height = sc.nextDouble();
        System.out.print("\tEnter width: ");
        this.width = sc.nextDouble();
        sc.close();
        System.out.println("");
    }

    public void caculateArea() {
        setArea(width * height);
        System.out.println(Thread.currentThread().getName()
                + ":\n\t Area of the rectangle: " + getArea() + "\n");
    }

    public void caculatePerimeter() {
        setPerimeter((width + height) * 2);
        System.out.println(Thread.currentThread().getName()
                + ":\n\t Perimeter of the rectangle: " + getPerimeter() + "\n");
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.inputData();
            }
        }, "Input Thread");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.caculateArea();

            }
        }, "Area Thread");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.caculatePerimeter();

            }
        }, "Perimeter Thread");

        t1.start();
        t1.join();

        t2.start();
        t3.start();

        t2.join();
        t3.join();

        System.out.println("Main Thread: \n\t Area: " + t.getArea()
                + " \n\tPerimeter: " + t.getPerimeter());
    }
}
