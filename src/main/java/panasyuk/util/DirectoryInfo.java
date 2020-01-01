package panasyuk.util;

import java.util.List;
import java.util.Objects;

public class DirectoryInfo {
    private String path;
    private List<String> fileNameList;

    public DirectoryInfo(String path, List<String> fileNameList) {
        this.path = path;
        this.fileNameList = fileNameList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<String> fileNameList) {
        this.fileNameList = fileNameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryInfo that = (DirectoryInfo) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(fileNameList, that.fileNameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, fileNameList);
    }
}
