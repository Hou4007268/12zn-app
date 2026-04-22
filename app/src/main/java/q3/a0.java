package q3;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class a0 {
    private final OkHttpClient client;

    public a0(z zVar) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(zVar.f4205r, TimeUnit.MILLISECONDS);
        builder.readTimeout(zVar.f4206s, TimeUnit.MILLISECONDS);
        client = builder.build();
    }

    public d a(d0 request) {
        return new d(client.newCall(request.a()));
    }
}
