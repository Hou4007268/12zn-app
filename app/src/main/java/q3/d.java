package q3;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class d {
    private final Call call;

    public d(Call call) {
        this.call = call;
    }

    public void f(final e callback) {
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(d.this, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(d.this, new h0(response));
            }
        });
    }

    public h0 g() throws IOException {
        return new h0(call.execute());
    }
}
