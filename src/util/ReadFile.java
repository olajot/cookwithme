package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadFile {

    private String[] parsedRecipe = new String[3];

    public String[] processFile(String path) {
        try {
            File recipeFile = new File(path);
            Scanner scanner = new Scanner(recipeFile);
            int i = 0;
            scanner.useDelimiter("<");

            while (scanner.hasNext()) {
                String text = scanner.next();
                int lastIndex = text.indexOf(">");
                String value = text.substring(lastIndex + 1).trim();
                parsedRecipe[i] = value;
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Another error");
        }
        return parsedRecipe;
    }

    public boolean checkIfFileValid(String path) {
        return isInFile(path, "<title>") && isInFile(path, "<ingredients>");
    }

    private boolean isInFile(String path, String tag) {
        try (FileReader fileReader = new FileReader(path);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = bufferedReader.readLine();

            while (line != null) {
                if (line.contains(tag)) {
                    return true;
                }
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
