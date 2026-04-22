package q3;

import okhttp3.Request;
import okhttp3.RequestBody;

public class c0 {
    private final Request.Builder builder = new Request.Builder();

    public void b(String name, String value) {
        builder.header(name, value);
    }

    public void d(String url) {
        builder.url(url);
    }

    public void c(String method, f0 body) {
        RequestBody rb = body == null ? null : body.a();
        builder.method(method, rb);
    }

    public d0 a() {
        return new d0(builder.build());
    }
}
