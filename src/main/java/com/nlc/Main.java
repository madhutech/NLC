package com.nlc;

import java.util.Scanner;

import static com.nlc.cal.Calculate.parseInputString;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        do {
            System.out.println("Please enter:");
            try {
                System.out.println(parseInputString(stdin.nextLine()));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (true);
    }
}
