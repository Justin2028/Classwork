/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.binarySearchTrees;

import java.util.Scanner;

public class TreeTest {

    public static void menu() {
        System.out.println("1 - Insert a Value");
        System.out.println("2 - Print the Tree");
        System.out.println("3 - Quit");
        System.out.println("4 - Print the Max Value");
        System.out.println("5 - Print the Min Value");
        System.out.println("6 - Print Leaves");
        System.out.println("7 - Find Value");
        System.out.println("8 - Get Max Prime");
        System.out.println("9 - Print Paths");
        System.out.println("10 - Find Path");
        System.out.println("11 - Print Branches");
    }

    public static void main(String[] args) {
        System.out.println("Binary Search Trees");

        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);

        Tree tree = new Tree();
        boolean flag = true;
        int status = 0, choice = 0;

        while (flag) {
            menu();
            status = 0;

            try {
                choice = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println("Please choose a valid option.");
                status = 1;
            }

            if (choice == 1) {
                System.out.println("Input value");
                int input = scan1.nextInt();
                tree.insert(input);
            } else if (choice == 2) {
                tree.print();
            } else if (choice == 3) {
                flag = false;
                System.out.println("End of program");
            } else if (choice == 4) {
                tree.printMax();
            } else if (choice == 5) {
                tree.printMin();
            } else if (choice == 6) {
                tree.printLeaves();
            } else if (choice == 7) {
                System.out.println("Input value");
                int input = scan1.nextInt();
                tree.search(input);
            } else if (choice == 8) {
                tree.getMaxPrime();
            } else if (choice == 9) {
                tree.printPaths();
            } else if (choice == 10) {
                boolean err = true;
                if(tree.isEmpty()) {
                    err=false;
                    System.out.println("Error : Empty Tree");
                }
                while (err) {
                    System.out.println("Input value");
                    int input = scan1.nextInt();
                    if (tree.getPath(new Node(input)) != null) {
                        System.out.println(tree.getPath(new Node(input)).toString());
                        err = false;
                    } else {
                        System.out.println("Node not found");
                    }
                }
            } else if (choice == 11) {
                tree.printBranches();
            } else if (status == 0) {
                System.out.println("Please choose a valid option.");
            }
            System.out.println("\n");
        }
    }
}
