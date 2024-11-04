import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    
    public static void main(String[] args) throws Exception {
       
        Scanner snex = new Scanner(System.in);

        int length;

        System.out.println("Enter the number of different words you will be searching for: ");
        length = snex.nextInt();
        snex.nextLine();

        int[] numWords = new int[length];

        String[] words = new String[length];

        for(int i = 0; i < length; i++){

            System.out.print("Enter word " + (i+1) + " to search for: ");
            words[i] = snex.nextLine();
        }

        System.out.println("What file would you like to search these words for (do not add .txt): ");
        String filename = snex.nextLine()+".txt";

        try {
            countWords(filename, words, numWords);

            System.out.println("Word occurrences:");
            for (int i = 0; i < length; i++) {
                System.out.println(words[i] + " " + numWords[i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        snex.close();
    }

    public static void countWords(String filename, String[] words, int[] count) throws FileNotFoundException {
        File file = new File(filename);
        Scanner filescan = new Scanner(file);

        while (filescan.hasNextLine()) {
            String line = filescan.nextLine();
            String[] lineWords = line.split("\\W+");

            for (String lineWord : lineWords) {
                lineWord = lineWord.trim().toLowerCase();

                for (int i = 0; i < words.length; i++) {
                    if (lineWord.equalsIgnoreCase(words[i])) {
                        count[i]++;
                    }
                }
            }
        }

        filescan.close();
    }
}
