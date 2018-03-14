package juja.sqlcmd.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Console implements View {
    private InputStream inputStream;
    private OutputStream outputStream;

    public Console() {
        this(System.out, System.in);
    }

    public Console(OutputStream outputStream, InputStream inputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
    }

    @Override
    public void write(String message) {
        try {
            outputStream.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Something goes wrong with write method... Reason:" + e.getMessage());
        }
    }

    @Override
    public String read() {
        StringBuilder line = new StringBuilder();
        try {
            while (inputStream.available() > 0) {
                char symbol = (char) inputStream.read();
                line.append(symbol);
            }
            return line.toString();
        } catch (IOException e) {
            throw new RuntimeException("Something goes wrong with read method... Reason:" + e.getMessage());
        }
    }
}