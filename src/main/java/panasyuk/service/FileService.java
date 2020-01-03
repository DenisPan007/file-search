package panasyuk.service;

import panasyuk.util.DirectoryInfo;
import panasyuk.util.FileUtil;

import java.util.Map;
import java.util.stream.Collectors;

public class FileService {

    FileUtil fileUtil = new FileUtil();

    public Map<String, String> getTextFiles(String filePath) {

        DirectoryInfo directoryInfo = fileUtil.getDirectoryInfoDto(filePath);
        return directoryInfo.getFileNameMap().entrySet().stream()
                .filter((fileItem) -> fileItem.getKey().endsWith(".txt"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
