package panasyuk.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    //todo add  ability of using relative paths
    public DirectoryInfo getDirectoryInfoDto(String pathStr) {
        File file = getFile(pathStr);
        File[] innerFilesList = file.listFiles();

        List<String> innerFileNames = Stream.of(innerFilesList)
                .map((fileItem)-> getFileName(fileItem.getPath(), pathStr))
                .collect(Collectors.toList());

        return new DirectoryInfo(pathStr, innerFileNames);
    }

    private File getFile(String pathStr) {
            Path path = Paths.get(pathStr);
            File file = path.toFile();
            if (!file.exists()) {
                throw new FileNotFoundException("File or directory not found for specified path");
            }
            return file;
    }

    private String getFileName(String filePath, String parentPath) {

        return filePath.replace(parentPath + '\\', "");
    }
}
