package panasyuk;

import panasyuk.file.FileService;
import panasyuk.search.SearchService;

import java.util.*;
import java.util.stream.Collectors;

import static panasyuk.Constants.EXIT_FROM_CONSOLE;
import static panasyuk.Constants.WORDS_DELIMITER;

public class FileSearcher {


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String filePath = parseFilePath(args);
            FileService fileService = new FileService();
            SearchService searchService = new SearchService();

            Map<String, String> textFiles = fileService.getTextFiles(filePath);
            List<String> textFileNames = new ArrayList<>(textFiles.keySet());
            System.out.println(textFileNames.size() + " text files read from directory " + filePath);

            String input;
            while (true) {
                System.out.print("search> ");
                input = scanner.nextLine();

                if (input.isEmpty()) {
                    System.out.println("No words given to search");
                }

                if (EXIT_FROM_CONSOLE.equals(input)) {
                    break;
                }

                Map<String, Double> searchResult = searchService.getFileNamesWithOverlapFactorMap(parseInputWords(input), textFiles);

                if (isAllFactorsEqualsZero(searchResult)) {
                    System.out.println("No matches found");
                } else {
                    searchResult.entrySet().stream()
                            .limit(10)
                            .sorted((entry1, entry2) -> -Double.compare(entry1.getValue(), entry2.getValue()))
                            .collect(Collectors.toCollection(LinkedHashSet::new))
                            .forEach(entry -> System.out.println(entry.getKey() + ':' + String.format("%.1f%%", entry.getValue())));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isAllFactorsEqualsZero(Map<String, Double> searchResult) {
        return searchResult.entrySet().stream().allMatch(entry -> entry.getValue() == 0);
    }

    private static String parseFilePath(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("No directory given to index");
        }
        return args[0];
    }

    private static List<String> parseInputWords(String inputWords) {
        return Arrays.asList(inputWords.split(WORDS_DELIMITER));
    }

}
