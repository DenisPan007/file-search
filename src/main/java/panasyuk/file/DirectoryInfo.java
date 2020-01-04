package panasyuk.file;

import java.util.Map;
import java.util.Objects;

public class DirectoryInfo {
    private String path;
    private Map<String, String> fileNameMap;

    public DirectoryInfo(String path, Map<String, String> fileNameMap) {
        this.path = path;
        this.fileNameMap = fileNameMap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFileNameMap() {
        return fileNameMap;
    }

    public void setFileNameMap(Map<String, String> fileNameMap) {
        this.fileNameMap = fileNameMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectoryInfo that = (DirectoryInfo) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(fileNameMap, that.fileNameMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, fileNameMap);
    }
}
