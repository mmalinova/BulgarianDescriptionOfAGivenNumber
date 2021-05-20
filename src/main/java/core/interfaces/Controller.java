package core.interfaces;

public interface Controller {
    void spellingSingleDigit(int n, boolean isNegative);

    void spellingDoubleDigits(int n, boolean isNegative, boolean isFromAnotherDigit);

    void spellingThreeDigits(int n, boolean isNegative, boolean isFromAnotherDigit);

    void spellingFourDigits(int n, boolean isNegative);

    void spellingFiveDigits(int n, boolean isNegative);

    void spellingSixDigits(int n, boolean isNegative);

    void spellingSevenDigits(int n, boolean isNegative);

    void spellingEightDigits(int n, boolean isNegative);

    void spellingNineDigits(int n, boolean isNegative);

    void spellingTenDigits(int n, boolean isNegative);
}

