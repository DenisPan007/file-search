package panasyuk.search;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void getFileNamesWithOverlapFactorMap() {
        String content = "words for search";
        List<String> wordsForSearch = Arrays.asList("test", "words", "for", "search");

        Map<String, String> testFilesMap = new HashMap<>();
        testFilesMap.put("testFileName", content);

        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("testFileName", 75.0);

        SearchService searchService = new SearchService();
        Map<String, Double> actualResult = searchService.getFileNamesWithOverlapFactorMap(wordsForSearch, testFilesMap);

        assertEquals(expectedResult, actualResult);
    }
}