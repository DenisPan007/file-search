package panasyuk.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    //todo add  ability of using relative paths
    public DirectoryInfo getDirectoryInfoDto(String pathStr) {
        File file = getFile(pathStr);
        try {
            File[] innerFilesList = file.listFiles();
            Map<String, String> innerFiles = new HashMap<>();
            for (File fileItem : innerFilesList) {
                try {
                    innerFiles.put(getFileName(fileItem.getPath(), pathStr), getFileContent(fileItem));
                }
                catch (AccessDeniedException ex){
                   //do nothing
                }
            }
            return new DirectoryInfo(pathStr, innerFiles);
        } catch (Exception e) {
            //todo custom exception?
            throw new RuntimeException("Exception while reading file");
        }
    }

    private String getFileContent(File fileItem) throws IOException {
        return String.join("", Files.readAllLines(Paths.get(fileItem.getPath())));
    }

    private File getFile(String pathStr) {
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
