package panasyuk.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileService {

    public Map<String, String> getTextFiles(String filePath) {

        Map<String, String> innerFiles = getInnerFiles(filePath);
        return innerFiles.entrySet().stream()
                .filter((fileItem) -> fileItem.getKey().endsWith(".txt"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, String> getInnerFiles(String pathStr) {
        File file = getFile(pathStr);
        try {
            File[] innerFilesList = file.listFiles();
            Map<String, String> innerFiles = new HashMap<>();
            for (File fileItem : innerFilesList) {
                try {
                    innerFiles.put(getFileName(fileItem.getPath(), pathStr), getFileContent(fileItem));
                } catch (AccessDeniedException ex) {
                    //do nothing
                }
            }
            return innerFiles;
        } catch (Exception e) {
            throw new RuntimeException("Exception while reading file");
        }
    }

    private String getFileContent(File fileItem) throws IOException {
        return String.join("", Files.readAllLines(Paths.get(fileItem.getPath())));
    }

    File getFile(String pathStr) {
        Path path = Paths.get(pathStr);
        File file = path.toFile();
        if (!file.exists()) {
            throw new FileNotFoundException("File or directory not found for specified path: " + pathStr);
        }
        return file;
    }

    private String getFileName(String filePath, String parentPath) {

        return filePath.replace(parentPath + '\\', "");
    }
}
