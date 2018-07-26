/**
 * Display words in ascending alphabetical order
 * Written by Daniel de Sao Jose
 *
 * (Display words in ascending alphabetical order) Write a program that reads
 * words from a text file and displays all the words (duplicates allowed) in ascend-
 * ing alphabetical order. The words must start with a letter. The text file is passed
 * as a command-line argument.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class DanielAssignment6 {

    public static void main (String[] args) {

        // Create objects that will help with reading files.
        File file;
        Scanner input;
        String line;
        ArrayList<String> wordList = new ArrayList<>();

        // Print help message if anything but a single argument is provided.
        if(!(args.length == 1)) {
            System.out.println("DanielAssignment6 takes only one argument.");
            System.exit(1);
        }

        // Attempt to open and process file. Fail gracefully if there is no file.
        try {
            file = new File(args[0]);
            input = new Scanner(file);

            // Iterator over lines in a file.
            while(input.hasNext()) {
                line = input.nextLine();

                // Split each line by whitespace and punctuation.
                for (String s : line.split("[\\s\\p{Punct}]+")) {

                    Boolean startsWithNumber = false;

                    // Ensure that the words do not start with a number.
                    for(int i = 0; i < 10; i++) {
                        if(s.startsWith(String.valueOf(i))) {
                            startsWithNumber = true;
                        }
                    }

                    // Add word to list if it doesn't start with a number.
                    if(!(startsWithNumber)) {
                        wordList.add(s);
                    }
                }
            }

            // Sort the list using the natural sort order.
            Collections.sort(wordList);

            // Print the sorted list.
            for (String s : wordList) {
                System.out.println(s);
            }

        } catch(FileNotFoundException e) {
            System.out.println("File does not exist!");
            System.exit(1);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
