import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        File dir = new File("C:/AmericasCardroom");
        try {
            System.out.println("Общий размер директории и вложенных файлов: " + getSizeToString(dir));
        } catch (IOException e) {
            e.printStackTrace();
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

    static String getSizeToString(File dir) throws IOException {

        long bytes = getDirSize(dir);
        float kB = (float) bytes / 1024;
        float mB = kB / 1024;
        float gB = mB / 1024;
        if (gB >= 1) {
            return String.format(Locale.CANADA_FRENCH,"%.2f ГБ (%,d байт)",gB,bytes);
        } else if (mB >= 1) {
            return String.format(Locale.CANADA_FRENCH,"%.2f МБ (%,d байт)", mB, bytes);
        } else if (kB >= 1) {
            return String.format(Locale.CANADA_FRENCH,"%.2f КБ (%,d байт)", kB, bytes);
        }
        return String.format(Locale.CANADA_FRENCH,"%,d байт", bytes);
    }
}
