package net.skyexcel.server.skyblock.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileUtils {

    public static boolean deleteFolder(File file) {
        try (Stream<Path> files = Files.walk(file.toPath())) {

            files.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            return true;
        } catch (IOException e) {

            return false;
        }
    }
}
