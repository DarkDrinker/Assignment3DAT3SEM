package Week1.Everything;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static List<Book> readBooksFromCSV(String filePath) {
        List<Book> books = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            // Skip the first line (headers)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Assuming CSV structure: Title|Author|Rating|Year Published
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 4) {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    double rating = Double.parseDouble(parts[2].trim());
                    int yearPublished = Integer.parseInt(parts[3].trim());

                    Book book = new Book(title, author, rating, yearPublished);
                    books.add(book);
                } else {
                    System.out.println("Invalid data in CSV line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        }

        return books;
    }
}