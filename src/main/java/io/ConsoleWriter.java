package io;

import io.interfaces.OutputWriter;

public class ConsoleWriter implements OutputWriter {

    @Override
    public void writeLine(String text) {
        System.out.print(text);
    }
}
