/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chuong3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kai
 */
public class Bai6 {

    private static final String FILE_NAME = "bai6.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        do {

            System.out.println("=====MENU=====");
            System.out.println("1. Truy cap trang web. \n"
                    + "2. Xem lich su truy cap. \n"
                    + "3. Exit \n");
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
                    System.out.println("=====BYE BYE=====");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Input number between 1~3!");
                    isCorrect = false;
            }

            System.out.print("Press 'Y' to continue!!");
            s = sc.nextLine();
        } while (s.equalsIgnoreCase("y"));
    }

    public static void task1() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter hostname: ");
        String hostname = sc.nextLine();

        FileWriter fw = null;

        try {

            hostname = hostname.replace("http://", "");
            hostname = hostname.replace("https://", "");
            hostname = hostname.replace("http", "");
            hostname = hostname.replace("https", "");

            URL url = new URL("https", hostname, "");

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

            String s = hostname + " / " + InetAddress.getByName(hostname).getHostAddress() + "\n";

            fw = new FileWriter(FILE_NAME);
            fw.append(s);

        } catch (UnknownHostException ex) {
            System.out.println("Invalid Hostname");
        } catch (MalformedURLException | IllegalArgumentException ex) {
            System.out.println("Invalid URL");
        } catch (IOException ex) {
            Logger.getLogger(Bai5.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Bai6.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void task2() {
        FileReader fr = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            fr = new FileReader(FILE_NAME);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }

            System.out.println(stringBuilder.toString());

        } catch (FileNotFoundException ex) {
            System.out.println("Lịch sử trống!");
        } catch (IOException ex) {
            Logger.getLogger(Bai6.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Bai6.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
