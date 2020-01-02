package panasyuk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSearcher {


    public static void main(String[] args) {
        try {
            String filePath = parseFilePath(args);
            FileService fileService = new FileService();
            List<String> textFileNames = new ArrayList<>(fileService.getTextFiles(filePath).keySet());
            System.out.println(textFileNames.size() + " text files read from directory " + filePath);


            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("search> ");
                String input = scanner.nextLine();
                System.out.println("Input was: " + input);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //todo add validation
    private static String parseFilePath(String[] args) {
        if (args.length == 0){
            throw new RuntimeException("No directory given to index");
        }
        return args[0];
    }

}
