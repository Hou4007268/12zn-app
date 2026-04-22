package c4;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class f extends FilterInputStream {
    public f(InputStream in) {
        super(in);
    }

    public static f fromBytes(byte[] bytes) {
        return new f(new ByteArrayInputStream(bytes == null ? new byte[0] : bytes));
    }

    public f y() {
        return this;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}
