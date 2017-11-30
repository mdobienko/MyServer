package com.md;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Marcin Dobienko
 */

public class DataSource {

    private static String newPattern = "^[0-9]{4}$";

    public static int getInt(){

        Scanner s = new Scanner(System.in);

        System.out.println("Enter socket, preferred from 5000 to 5555, current range 1-9999");

        while (true) {
            try {
               return s.nextInt();

            } catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("Incorrect, please try again");

            }
        }

    }

    public void EnterData() {

        // Enter valid socket

        int mySocketInt = getInt();
        String mySocketString = Integer.toString(mySocketInt);

        Pattern pattern = Pattern.compile(newPattern);
        Matcher matcher = pattern.matcher(mySocketString);
        matcher.reset();

        if(matcher.find()){
            System.out.println("Correct current socket is: " +mySocketInt);

            try (ServerSocket serverSocket = new ServerSocket(mySocketInt)) {
                while (true) {
                    System.out.println("Connected");
                    new Echo(serverSocket.accept()).start();
                }

            } catch (IOException e) {
                System.out.println("Server exception " + e.getMessage());
            }

        } else {
            System.out.println("Not available range, please try next time");
        }
    }
}
