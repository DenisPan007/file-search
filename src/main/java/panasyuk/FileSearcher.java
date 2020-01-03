package panasyuk;

import panasyuk.service.FileService;
import panasyuk.service.SearchService;

import java.util.*;

import static panasyuk.Constants.EXIT_FROM_CONSOLE;
import static panasyuk.Constants.WORDS_DELIMITER;

public class FileSearcher {




    public static void main(String[] args) {
        try {
            String filePath = parseFilePath(args);
            FileService fileService = new FileService();
            SearchService searchService = new SearchService();
            Map<String, String> textFiles = fileService.getTextFiles(filePath);
            List<String> textFileNames = new ArrayList<>(textFiles.keySet());
            System.out.println(textFileNames.size() + " text files read from directory " + filePath);

                try (Scanner scanner = new Scanner(System.in)) {
                    System.out.print("search> ");
                    String input;
                    while (true){
                        input = scanner.nextLine();
                        if (EXIT_FROM_CONSOLE.equals(input)){
                            break;
                        }
                        Map<String, String> searchResult = searchService.getFileNamesWithOverlapFactorMap(parseInputWords(input), textFiles);
                        searchResult.forEach((key, value) -> System.out.println(key + ':' + value));
                    }
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

    private static List<String> parseInputWords(String inputWords){
        if (inputWords == null || inputWords.isEmpty()){
            //todo custom exception and handling
            throw new RuntimeException("No directory given to index");
        }
        return Arrays.asList(inputWords.split(WORDS_DELIMITER));
    }

}
