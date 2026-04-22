package q3;

import c4.f;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import okhttp3.ResponseBody;

public class i0 extends j0 {
    public final f f4117c;
    private final String text;

    public i0(ResponseBody body) {
        byte[] bytes;
        String tmp;
        try {
            if (body == null) {
                bytes = new byte[0];
                tmp = "";
            } else {
                bytes = body.bytes();
                tmp = new String(bytes, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            bytes = new byte[0];
            tmp = "";
        }
        this.f4117c = new f(new ByteArrayInputStream(bytes));
        this.text = tmp;
    }

    @Override
    public String C() {
        return text;
    }
}
