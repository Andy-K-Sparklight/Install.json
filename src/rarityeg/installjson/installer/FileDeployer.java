package rarityeg.installjson.installer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.List;

public final class FileDeployer {
    static String MODS_ROOT = "mods";

    static void deployMods(List<String> files, String dir) throws IOException {
        for (String f : files) {
            File file = new File(f);
            File target = new File(Paths.get(dir, MODS_ROOT, Paths.get(f).getFileName().toString()).normalize().toString());
            try (FileChannel inputChannel = new FileInputStream(file).getChannel(); FileChannel outputChannel = new FileOutputStream(target).getChannel()) {
                outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
                System.out.println(file.getAbsolutePath() + " -> " + target.getAbsolutePath() + " [COPIED]");
            }
        }
    }
}
