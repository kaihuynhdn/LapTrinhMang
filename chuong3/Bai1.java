/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuong3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kai
 */
public class Bai1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            System.out.println("=====MENU=====");
            System.out.println("1. Câu 1. \n"
                    + "2. Câu 2. \n"
                    + "3. Câu 3. \n"
                    + "4. Câu 4. \n"
                    + "5. Exit \n");
            System.out.print("Input a number: ");
            int number = 0;
            boolean b = false;
            while (!b) {
                String i = sc.nextLine();
                try {
                    number = Integer.parseInt(i);
                    b = true;
                } catch (Exception ex) {
                    System.out.print("Data input is invalid!"
                            + "Please input again: ");
                }
            }
            boolean isCorrect = true;
            switch (number) {
                case 1:
                    task1();
                    break;
                case 2:
                    task2();
                    break;
                case 3:
                    task3();
                    break;
                case 4:
                    task4();
                    break;
                case 5:
                    System.out.println("=====BYE BYE=====");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Input number between 1~5!");
                    isCorrect = false;
            }
            if (!isCorrect) {
                s = "y";
            } else {
                System.out.print("Press 'Y' to continue!!");
                s = sc.nextLine();
            }
        } while (s.equalsIgnoreCase("y"));
    }

    public static void task1() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter URL: ");

        String s = scanner.nextLine();

        try {
            URL url = new URL(s);

            String info = "\tFile name:\t" + url.getFile()
                    + "\n\tHost name:\t" + url.getHost()
                    + "\n\tPort:\t\t" + url.getDefaultPort()
                    + "\n\tProtocol:\t" + url.getProtocol();

            System.out.println(info);

        } catch (MalformedURLException ex) {
            System.out.println("Invalid URL!");
        }
    }

    public static void task2() {
        Scanner sc = new Scanner(System.in);

        String u = "";
        System.out.print("Enter URL: ");
        u = sc.nextLine();
        try {
            URL url = new URL(u);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            StringBuilder builder;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
            }

            System.out.println(builder.toString());

        } catch (MalformedURLException ex) {
            System.out.println("Invalid URL");
        } catch (IOException ex) {
            Logger.getLogger(Bai1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void task3() {
        try {

            System.out.print("Enter URL: ");

            System.out.println("LocalHost:\t" + InetAddress.getLocalHost()
                    + "\nAddress:\t" + InetAddress.getByName("google.com"));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Bai1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void task4() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter URL: ");
            String u = sc.nextLine();

            URL url = new URL(u);

            URLConnection conn = url.openConnection();
            System.out.println("Content-type: " + conn.getContentType() + "\n"
                    + "Content-encoding: " + conn.getContentEncoding() + "\n"
                    + "Date: " + new Date(conn.getDate()) + "\n"
                    + "Last modified: " + new Date(conn.getLastModified()) + "\n"
                    + "Expiration date: " + new Date(conn.getExpiration()) + "\n"
                    + "Content-length: " + conn.getContentLength());
        } catch (MalformedURLException ex) {
            System.out.println("Invalid URL!");
        } catch (IOException ex) {
            Logger.getLogger(Bai1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
