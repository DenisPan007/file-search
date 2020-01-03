package panasyuk.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static panasyuk.Constants.WORDS_DELIMITER;

public class SearchService {

    private static final int DECIMAL_PLACES = 1;

    public Map<String, String> getFileNamesWithOverlapFactorMap(List<String> inputWords, Map<String, String> filesMap) {
        String outputPattern = "%."+ DECIMAL_PLACES + "f%%";
        return filesMap.entrySet().stream()
                .collect(Collectors.
                        toMap(Map.Entry::getKey, entry -> String.format(outputPattern, getOverlapFactor(inputWords, entry.getValue()))) );

    }


    private double getOverlapFactor(List<String> inputWords, String fileContent) {
        int inputWordsCount = inputWords.size();

        int overlapCount = 0;
        List<String> fileContentArray = Arrays.asList(fileContent.split(WORDS_DELIMITER));
        for (String word: inputWords){
            if (fileContentArray.contains(word)){
                overlapCount++;
            }

        }
        double result = 100 * (double)overlapCount/inputWordsCount;
        return round(result);
    }

    private double round(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
