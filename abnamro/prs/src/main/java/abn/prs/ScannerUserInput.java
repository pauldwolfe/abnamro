package abn.prs;

import java.util.Scanner;

public class ScannerUserInput implements UserInput{

    private final Scanner scanner;

    ScannerUserInput(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String input() {
        return scanner.next();
    }
}
