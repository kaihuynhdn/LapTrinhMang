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
public class Bai5 {

    private static final String[] forbiddenList = {"zing.vn",
        "vnexpress.net", "nhaccuatui.com", "ctsv.ued.udn.vn"};

    public static boolean isForbiddenUrl(String hostname) throws UnknownHostException {
        for (String s : forbiddenList) {
            InetAddress ia1 = InetAddress.getByName(s);
            InetAddress ia2 = InetAddress.getByName(hostname);

            if (ia1.getHostAddress().equalsIgnoreCase(ia2.getHostAddress())) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = "";
        do {
            try {

                System.out.print("Enter hostname/IP address: ");
                String hostname = sc.nextLine();

                if (isForbiddenUrl(hostname)) {
                    System.out.println("Bạn đang truy cập đến trang web trong"
                            + " danh sách cấm!\n Vui lòng thử lại sau.");
                } else {
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
                }

            } catch (UnknownHostException ex) {
                System.out.println("Invalid Hostname");
            } catch (MalformedURLException ex) {
                Logger.getLogger(Bai5.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Bai5.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.print("Press 'Y' to continue!!");
            s = sc.nextLine();
        } while (s.equalsIgnoreCase("y"));
    }

}
