package rarityeg.installjson.installer;

public class Main {
    public static void main(String[] args) {
        if ("1".equals(System.getProperty("rshowlicense"))) {
            System.out.println("Install.json to help Minecraft Launcher to deploy mods.\n" +
                    "Copyright (C) 2021  Andy K Rarity Sparklight\n" +
                    "\n" +
                    "This program is free software: you can redistribute it and/or modify\n" +
                    "it under the terms of the GNU General Public License as published by\n" +
                    "the Free Software Foundation, version 3." +
                    "\n" +
                    "This program is distributed in the hope that it will be useful,\n" +
                    "but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
                    "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
                    "GNU General Public License for more details.\n" +
                    "\n" +
                    "You should have received a copy of the GNU General Public License\n" +
                    "along with this program.  If not, see <https://www.gnu.org/licenses/>.");
        } else {
            System.out.println("Install.json  Copyright (C) 2021  Andy K Rarity Sparklight\n" +
                    "This program comes with ABSOLUTELY NO WARRANTY; for details, run with `-Drshowlicense=1`.\n" +
                    "This is free software, and you are welcome to redistribute it\n" +
                    "under certain conditions; run with `-Dshowlicense=1` to see.");

        }
        String dir = args[0] == null ? "." : args[0];
        try {
            FileDeployer.deployFiles(Util.generateSpecifiedFiles(), dir);
            Util.showCompleted();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
