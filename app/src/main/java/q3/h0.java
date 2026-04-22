package q3;

import java.io.IOException;
import okhttp3.Response;

public class h0 {
    public final int f4101d;
    public final j0 f4104g;
    private final Response response;

    public h0(Response response) {
        this.response = response;
        this.f4101d = response.code();
        this.f4104g = new i0(response.body());
    }

    public boolean C() {
        return response.isSuccessful();
    }

    public void close() {
        try {
            if (response.body() != null) response.body().close();
        } catch (Exception ignored) {
        }
    }
}
