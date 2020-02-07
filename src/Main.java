import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        File dir = new File("C:/AmericasCardroom");
        try {
            System.out.printf("Общий размер директории и вложенных файлов: %d байт", getDirSize(dir));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static long getDirSize(File dir) throws IOException {

        return Files.walk(dir.toPath()).map(Path::toFile)
                .filter(file -> file.isFile() & !Files.isSymbolicLink(file.toPath()))
                .map(File::length).reduce(Long::sum).get();
    }
}
