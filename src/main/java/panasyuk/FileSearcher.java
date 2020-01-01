package panasyuk;

import panasyuk.util.DirectoryInfo;
import panasyuk.util.FileUtil;

public class FileSearcher {


    public static void main(String[] args) {
        String filePath = parseFilePath(args);
        FileUtil fileUtil = new FileUtil();
        DirectoryInfo directoryInfo = fileUtil.getDirectoryInfoDto(filePath);
        int size = directoryInfo.getFileNameList().size();
        System.out.println(size + " files read from directory " + filePath);
        directoryInfo.getFileNameList().forEach(System.out::println);
        System.out.println("Hello, Alena!");
    }

    //todo add validation
    private static String parseFilePath(String[] args) {
        return args[0];
    }

}
