package tennissimulator.menu;

import java.util.Scanner;

public class Button {

    private static final Scanner scanner = new Scanner(System.in);

    public static void pressKey() {
        System.out.println("\nPress <ENTER> key to simulate next hand...");
        scanner.nextLine();
    }

}