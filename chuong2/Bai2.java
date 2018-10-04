package chuong2; 

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kai
 */
class Rectangle {

    private double height;
    private double width;
    private double perimeter;
    private double area;
    
    private static boolean isInput = false;
    private static boolean isCaculatedArea = false;
    private static boolean isCauclatedPerimeter = false;

    public Rectangle() {

    }

    public synchronized void inputData() {
        System.out.println("LUỒNG NHẬP DỮ LIỆU đang hoạt động: ");

        Scanner sc = new Scanner(System.in);
        System.out.print("\tEnter height: ");
        this.height = sc.nextDouble();
        System.out.print("\tEnter width: ");
        this.width = sc.nextDouble();
        sc.close();
        isInput = true;
        System.out.println("");
    }

    public void caculateArea() {
        System.out.println("LUỒNG DIỆN TÍCH đang hoạt động");
        while(!isInput){
            System.out.println("Luồng Diện tích đang chờ LUỒNG NHẬP DỮ LIỆU\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rectangle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setArea(width * height);
        System.out.println(Thread.currentThread().getName()
                + ":\n\t Area of the rectangle: " + getArea() + "\n");
        isCaculatedArea = true;
    }

    public void caculatePerimeter() {
        System.out.println("LUỒNG CHU VI đang hoạt động");
        while(!isInput){
            System.out.println("Luồng Chu vi đang chờ LUỒNG NHẬP DỮ LIỆU\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rectangle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setPerimeter((width + height) * 2);
        System.out.println(Thread.currentThread().getName()
                + ":\n\t Perimeter of the rectangle: " + getPerimeter() + "\n");
        isCauclatedPerimeter = true;
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

    public static boolean isInput() {
        return isInput;
    }

    public static boolean isCaculatedArea() {
        return isCaculatedArea;
    }

    public static boolean isCauclatedPerimeter() {
        return isCauclatedPerimeter;
    }
    
    

    public static void main(String[] args) throws InterruptedException {
        Rectangle t = new Rectangle();

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
        t2.start();
        t3.start();
        
        while(!t.isInput() || !t.isCauclatedPerimeter()
                || !t.isCaculatedArea()){
            if (!t.isInput()) {
                System.out.println("LUỒNG CHÍNH đang chờ LUỒNG NHẬP DỮ LIỆU");
            }
            
            if (!t.isCaculatedArea()) {
                System.out.println("LUỒNG CHÍNH đang chờ kết quả LUỒNG DIỆN TÍCH");
            }
            
            if (!t.isCauclatedPerimeter()) {
                System.out.println("LUỒNG CHÍNH đang chờ kết quả LUỒNG CHU VI\n");
            }
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Rectangle.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("");
        }

        System.out.println("Main Thread: \n\t Area: " + t.getArea()
                + " \n\tPerimeter: " + t.getPerimeter());
    }
}
