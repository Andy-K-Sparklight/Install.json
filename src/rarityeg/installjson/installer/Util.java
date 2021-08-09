package rarityeg.installjson.installer;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Util {
    public static List<String> generateSpecifiedFiles() {
        String[] rawFiles = System.getProperty("rfiles").split(File.pathSeparator);
        String[] ignoreFiles = System.getProperty("rignore").split(File.pathSeparator);
        List<String> ls = new ArrayList<>();
        for (String f : rawFiles) {
            boolean skip = false;
            for (String i : ignoreFiles) {
                if (f.contains(i)) {
                    skip = true;
                    break;
                }
            }
            if (!skip) {
                ls.add(f);
            }
        }
        return ls;
    }

    public static void showCompleted() {
        String loader = System.getProperty("rsuggest");
        String version = System.getProperty("rversion");
        String type = System.getProperty("rshowhint").toLowerCase();
        String msgEN = "Install has completed, but you still need to install " + loader + " and Minecraft " + version + " to make it work.";
        String msgCN = "安装已经完成，但你仍然需要安装 " + loader + " 和 Minecraft " + version + " 才能开始畅玩。";
        if ("hint".equals(type)) {
            JOptionPane.showMessageDialog(null, msgEN + "\n" + msgCN, "Almost There 就快好了", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
