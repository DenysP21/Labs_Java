import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class CryptoReader extends FilterReader {
    private int key;

    public CryptoReader(Reader in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) return -1;
        return c - key;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int numChars = super.read(cbuf, off, len);
        if (numChars == -1) return -1;

        for (int i = off; i < off + numChars; i++) {
            cbuf[i] = (char) (cbuf[i] - key);
        }
        return numChars;
    }
}