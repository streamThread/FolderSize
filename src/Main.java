import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        File dir = new File("C:/AmericasCardroom");
        try {
            long bytes = getDirSize(dir);
            float kB = (float) bytes/1024;
            float mB = kB/1024;
            float gB = mB/1024;
            System.out.printf("Общий размер директории и вложенных файлов: " +
                    "%2$d байт\n%1$44s%3$.2f КБ\n%1$44s%4$.2f МБ\n%1$44s%5$.2f ГБ","", bytes,kB,mB,gB);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static long getDirSize(File dir) throws IOException {

        return Files.walk(dir.toPath())
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(file -> !Files.isSymbolicLink(file.toPath()))
                .mapToLong(File::length)
                .sum();
    }
}
