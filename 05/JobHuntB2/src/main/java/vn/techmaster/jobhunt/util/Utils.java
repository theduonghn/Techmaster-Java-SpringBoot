package vn.techmaster.jobhunt.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    // Lấy extension file
    public static String getExtensionFile(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    // Kiểm tra extenstion file có nằm trong danh sách được upload hay không
    public static boolean checkFileExtenstion(String fileExtension) {
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg", "webp");
        return fileExtensions.contains(fileExtension);
    }
}
