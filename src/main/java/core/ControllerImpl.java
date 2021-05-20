package core;

import core.interfaces.Controller;
import io.ConsoleWriter;
import io.interfaces.OutputWriter;

import java.io.*;

public class ControllerImpl implements Controller {
    private final String[] firstTen = {"нула", "едно", "две", "три", "четири", "пет", "шест", "седем", "осем", "девет"};
    private final String[] postfix = {"десет", "стотин", "хиляди", "милиона", "милиарда"};
    private final String[] repeating = {"един", "два"};
    private final OutputWriter writer = new ConsoleWriter();
    private final PrintWriter fileWriter = new PrintWriter("result.txt");

    public ControllerImpl() throws FileNotFoundException {
    }

    @Override
    public void spellingSingleDigit(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        this.writer.writeLine(firstTen[n]);
        this.fileWriter.print(firstTen[n]);
        // close file
        this.fileWriter.close();
    }

    @Override
    public void spellingDoubleDigits(int n, boolean isNegative, boolean isFromAnotherDigit) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 10;
        int secondDigit = n % 10;
        String post = postfix[0];
        char letterAnd = 'и';
        String firstWord;
        String words = null;

        if (isFromAnotherDigit) {
            this.writer.writeLine(String.format(" %c ", letterAnd));
        }
        if (firstDigit == 0) {
            spellingSingleDigit(secondDigit, isNegative);
            return;
        } else if (firstDigit == 1) {
            switch (secondDigit) {
                case 1:
                    words = "едина" + post;
                    break;
                case 2:
                    words = "двана" + post;
                    break;
                case 3:
                    words = "трина" + post;
                    break;
                case 4:
                    words = "четирина" + post;
                    break;
                case 5:
                    words = "петна" + post;
                    break;
                case 6:
                    words = "шестна" + post;
                    break;
                case 7:
                    words = "седемна" + post;
                    break;
                case 8:
                    words = "осемна" + post;
                    break;
                case 9:
                    words = "деветна" + post;
                    break;
                case 0:
                    words = post;
                    break;
            }
        } else {
            if (firstDigit == 2) {
                firstWord = repeating[1] + post;
            } else {
                firstWord = firstTen[firstDigit] + post;
            }
            if (secondDigit != 0) {
                words = String.format("%s %c %s", firstWord, letterAnd, firstTen[secondDigit]);
            } else {
                words = firstWord;
            }
        }
        this.writer.writeLine(words);
        this.fileWriter.print(words);
        // close file
        this.fileWriter.close();
    }

    @Override
    public void spellingThreeDigits(int n, boolean isNegative, boolean isFromAnotherDigit) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 100;
        int secondDigit = (n % 100) / 10;
        int thirdDigit = (n % 10) % 10;
        String post = postfix[1];
        String firstWord;
        String words;

        if (isFromAnotherDigit) {
            this.writer.writeLine(" ");
        }
        if (firstDigit == 0) {
            spellingDoubleDigits(n % 100, isNegative, isFromAnotherDigit);
            return;
        }
        if (firstDigit == 1) {
            firstWord = "сто";
        } else if (firstDigit == 2) {
            firstWord = "двеста";
        } else if (firstDigit == 3) {
            firstWord = "триста";
        } else {
            firstWord = firstTen[firstDigit] + post;
        }
        if (secondDigit == 0) {
            if (thirdDigit == 0) {
                words = firstWord;
            } else {
                this.writer.writeLine(firstWord);
                this.fileWriter.print(firstWord);
                spellingDoubleDigits(n % 100, isNegative, true);
                return;
            }
        } else if (thirdDigit == 0) {
            this.writer.writeLine(firstWord);
            this.fileWriter.print(firstWord);
            spellingDoubleDigits(n % 100, isNegative, true);
            return;
        } else {
            this.writer.writeLine(firstWord + " ");
            this.fileWriter.print(firstWord + " ");
            spellingDoubleDigits(n % 100, isNegative, false);
            return;
        }
        this.writer.writeLine(words);
        this.fileWriter.print(words);
        // close file
        this.fileWriter.close();
    }

    @Override
    public void spellingFourDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 1000;
        String post = postfix[2];

        if (firstDigit == 0) {
            spellingThreeDigits(n % 1000, isNegative, false);
        } else if (firstDigit == 1) {
            if (n % 1000 == 0) {
                this.writer.writeLine("хиляда");
                this.fileWriter.print("хиляда");
                return;
            }
            this.writer.writeLine("хиляда");
            this.fileWriter.print("хиляда");
            spellingThreeDigits(n % 1000, isNegative, true);
        } else if (n % 1000 == 0) {
            this.writer.writeLine(String.format("%s %s", firstTen[firstDigit], post));
            this.fileWriter.print(String.format("%s %s", firstTen[firstDigit], post));
        } else {
            this.writer.writeLine(String.format("%s %s", firstTen[firstDigit], post));
            this.fileWriter.print(String.format("%s %s", firstTen[firstDigit], post));
            spellingThreeDigits(n % 1000, isNegative, true);
        }
    }

    @Override
    public void spellingFiveDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 10000;
        int firstTwoDigits = n / 1000;
        String post = postfix[2];

        if (firstDigit == 0) {
            spellingFourDigits(n % 10000, isNegative);
        } else {
            spellingDoubleDigits(firstTwoDigits, isNegative, false);
            this.writer.writeLine(String.format(" %s", post));
            this.fileWriter.print(String.format(" %s", post));
            if (n % 1000 == 0) {
                return;
            }
            if ((n % 1000) / 10 == 1 || (n % 1000) / 10 == 0) {
                if ((n % 100) / 10 == 0 || (n % 100) / 10 == 1) {
                    spellingThreeDigits(n % 1000, isNegative, true);
                    return;
                }
            }
            this.writer.writeLine(" ");
            this.fileWriter.print(" ");
            spellingThreeDigits(n % 1000, isNegative, false);
        }
    }

    @Override
    public void spellingSixDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 100000;
        int firstTreeDigits = n / 1000;
        String post = postfix[2];

        if (firstDigit == 0) {
            spellingFiveDigits(n % 100000, isNegative);
        } else {
            spellingThreeDigits(firstTreeDigits, isNegative, false);
            this.writer.writeLine(String.format(" %s", post));
            this.fileWriter.print(String.format(" %s", post));
            if (n % 1000 == 0) {
                return;
            }
            if ((n % 1000) / 10 == 1 || (n % 1000) / 10 == 0) {
                if ((n % 1000) % 100 == 0 || (n % 1000) % 100 == 1) {
                    spellingThreeDigits(n % 10000, isNegative, true);
                    return;
                }
            }
            this.writer.writeLine(" ");
            this.fileWriter.print(" ");
            spellingThreeDigits(n % 1000, isNegative, false);
        }
    }

    @Override
    public void spellingSevenDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 1000000;
        int secondDigit = n / 100000;
        String post = postfix[3];

        if (firstDigit == 0) {
            spellingSixDigits(n % 1000000, isNegative);
        } else {
            if (firstDigit == 1) {
                this.writer.writeLine(repeating[0]);
                this.fileWriter.print(repeating[0]);
                this.writer.writeLine(" милион ");
                this.fileWriter.print(" милион ");
            } else {
                spellingSingleDigit(firstDigit, isNegative);
                this.writer.writeLine(post + " ");
                this.fileWriter.print(post + " ");
            }
            if (secondDigit != 0) {
                if (n % 100000 == 0) {
                    this.writer.writeLine("и ");
                    this.fileWriter.print("и ");
                }
            }
            if (n % 1000000 == 0) {
                return;
            }
            spellingSixDigits(n % 1000000, isNegative);
        }
    }


    @Override
    public void spellingEightDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 10000000;
        int firstTwoDigits = n / 1000000;
        String post = postfix[3];

        if (firstDigit == 0) {
            spellingSevenDigits(n % 10000000, isNegative);
        } else {
            spellingDoubleDigits(firstTwoDigits, isNegative, false);
            this.writer.writeLine(" " + post + " ");
            this.fileWriter.print(" " + post + " ");
        }
        if (n % 1000000 == 0) {
            return;
        }
        spellingSixDigits(n % 1000000, isNegative);
    }


    @Override
    public void spellingNineDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 100000000;
        String post = postfix[3];

        if (firstDigit == 0) {
            spellingEightDigits(n % 100000000, isNegative);
        } else {
            spellingThreeDigits(n / 1000000, isNegative, false);
            this.writer.writeLine(" " + post + " ");
            this.fileWriter.print(" " + post + " ");
        }
        if (n % 100000000 == 0) {
            return;
        }
        spellingSixDigits(n % 1000000, isNegative);
    }

    @Override
    public void spellingTenDigits(int n, boolean isNegative) {
        writeMinusIfTheNumberIsNegative(isNegative);
        int firstDigit = n / 1000000000;

        if (firstDigit == 0) {
            spellingNineDigits(n % 1000000000, isNegative);
        } else {
            if (firstDigit == 1) {
                this.writer.writeLine(repeating[0]);
                this.fileWriter.print(repeating[0]);
                this.writer.writeLine(" милиард ");
                this.fileWriter.print(" милиард ");
            } else {
                this.writer.writeLine(repeating[1]);
                this.fileWriter.print(repeating[1]);
                this.writer.writeLine(" милиарда ");
                this.fileWriter.print(" милиарда ");
            }
        }
        if (n % 100000000 == 0) {
            return;
        }
        spellingNineDigits(n % 1000000000, isNegative);
    }

    private void writeMinusIfTheNumberIsNegative(boolean isNegative) {
        if (isNegative) {
            this.writer.writeLine("минус ");
            this.fileWriter.print("минус ");
        }
    }
}
