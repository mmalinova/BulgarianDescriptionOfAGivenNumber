package core;

import core.interfaces.Controller;
import io.ConsoleReader;
import io.ConsoleWriter;
import io.interfaces.InputReader;
import io.interfaces.OutputWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static common.constants.ExceptionMessages.*;
import static common.constants.OutputMessages.*;

public class EngineImpl implements Runnable {
    private final InputReader reader;
    private final OutputWriter writer;
    private Controller controller = null;

    public EngineImpl() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        try {
            this.controller = new ControllerImpl();
        } catch (FileNotFoundException e) {
            this.writer.writeLine(FILE_NOT_FOUND);
        }

    }

    @Override
    public void run() {
        try {
            processInput();
        } catch (IllegalArgumentException | NullPointerException | IOException e) {
            this.writer.writeLine(e.getMessage());
        }
    }

    private void processInput() throws IOException {
        this.writer.writeLine(PROGRAM_DESC);
        this.writer.writeLine(TO_DESCRIBE);

        // read the input from the console
        String input = this.reader.readLine();
        int n = Integer.parseInt(input);
        boolean isNegative = false;
        int number = n;
        if (n < 0) {
            isNegative = true;
            // convert negative number to positive for easier process
            number = (n * -2) + n;
        }

        // switch over number of digits
        int length = input.trim().length();
        if (isNegative) {
            length -= 1;
        }
        switch (length) {
            case 1:
                this.controller.spellingSingleDigit(number, isNegative);
                break;
            case 2:
                this.controller.spellingDoubleDigits(number, isNegative, false);
                break;
            case 3:
                this.controller.spellingThreeDigits(number, isNegative, false);
                break;
            case 4:
                this.controller.spellingFourDigits(number, isNegative);
                break;
            case 5:
                this.controller.spellingFiveDigits(number, isNegative);
                break;
            case 6:
                this.controller.spellingSixDigits(number, isNegative);
                break;
            case 7:
                this.controller.spellingSevenDigits(number, isNegative);
                break;
            case 8:
                this.controller.spellingEightDigits(number, isNegative);
                break;
            case 9:
                this.controller.spellingNineDigits(number, isNegative);
                break;
            case 10:
                this.controller.spellingTenDigits(number, isNegative);
                break;
        }
    }
}

