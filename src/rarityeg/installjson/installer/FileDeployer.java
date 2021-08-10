package rarityeg.installjson.installer;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.List;

public final class FileDeployer {
    static String ROOT_FOLDER = ".";
    static String PLATFORM_IDENTIFIER = "rarityeg/installjson/inject/";
    static String ROOT_IDENTIFIER = "injectintoroot";

    @SuppressWarnings("ResultOfMethodCallIgnored")
    static void deployFiles(List<String> files, String dir) throws IOException {
        JFrame jf = null;
        JProgressBar progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(files.size());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        int configured = 0;
        if ("hint".equalsIgnoreCase(System.getProperty("rshowhint"))) {
            jf = new JFrame("Installing 执行中");
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.setLocationRelativeTo(null);
            jf.setSize(280, 100);
            JPanel panel = new JPanel();
            panel.add(progressBar);
            jf.setContentPane(panel);
            jf.setVisible(true);
        }
        for (String f : files) {
            try {
                File file = new File(f);
                File target = new File(Paths.get(dir, getTargetFolder(f), Paths.get(f).getFileName().toString()).normalize().toString());
                target.getParentFile().mkdirs();
                try (FileChannel inputChannel = new FileInputStream(file).getChannel(); FileChannel outputChannel = new FileOutputStream(target).getChannel()) {
                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
                    System.out.println(file.getAbsolutePath() + " -> " + target.getAbsolutePath() + " [COPIED]");
                }
                configured++;
                progressBar.setValue(configured);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        if (jf != null) {
            jf.setVisible(false);
        }

    }

    static String getTargetFolder(String fName) {
        String t = Paths.get(fName).normalize().toUri().toString();
        String w = t.split(PLATFORM_IDENTIFIER)[1];
        if (w != null) {
            String x = w.split("/")[0];
            if (x != null && x.length() > 0) {
                if (!x.equals(ROOT_IDENTIFIER)) {
                    return x;
                }
            }
        }
        return ROOT_FOLDER;
    }
}
