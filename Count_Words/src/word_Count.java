import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class word_Count {
    public static void SortAndCount(String file_name, String output_file) {
        // Create a map to store word counts (case-insensitive)
        Map<String, Integer> wordCounts = new HashMap<>();

        try (Scanner scanner = new Scanner(new File(file_name))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Convert the words to lowercase 
                String word = line.toLowerCase();
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
                // Convert the HashMap entries to a List
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCounts.entrySet());

        // Sort the list based on keys in alphabetical order
        sortedList.sort(Comparator.comparing(Map.Entry::getKey));
        
        try (FileWriter writer = new FileWriter(new File(output_file))) {
            for (Map.Entry<String, Integer> entry : sortedList) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.write("\n");
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Word counts have been written to " + output_file);
    }
    public static void main(String[] args) {
      Scanner file_name = new Scanner(System.in);
      System.out.print("Enter the file name to be read: ");
      String fileName = file_name.nextLine();

      Scanner output_file = new Scanner(System.in);
      System.out.print("Enter the name of the file to be outputted: ");
      String outputFile = file_name.nextLine();
      output_file.close();
      file_name.close();
      SortAndCount(fileName, outputFile);
    }
}
