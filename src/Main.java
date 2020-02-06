import java.io.File;

public class Main {
    public static void main(String[] args) {

        File dir = new File("C:/AmericasCardroom");
        try {
            System.out.printf("Общий размер директории и вложенных файлов: %d байт", getDirSize(dir));
        } catch (IllegalArgumentException iAE) {
            System.out.println(iAE.getMessage());
        }
    }

    static long getDirSize(File dir) throws IllegalArgumentException {
        long size = 0;
        if (dir == null) {
            throw new IllegalArgumentException("Директория пуста");
        }
        if (dir.isFile()) {
            size = dir.length();
        } else {
            File[] subFiles = dir.listFiles();
            if (subFiles != null) {
                for (File file : subFiles) {
                    if (file.isFile()) {
                        size += file.length();
                    } else {
                        size += getDirSize(file);
                    }
                }
            }
        }
        return size;
    }
}
