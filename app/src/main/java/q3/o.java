package q3;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.io.File;

public final class o {
    private o() {}

    public static MediaType r(String type) {
        return MediaType.get(type);
    }

    public static f0 f(String content, MediaType mediaType) {
        return new f0(RequestBody.create(content, mediaType));
    }

    public static MultipartBody.Part g(String name, String filename, e0 body) {
        return MultipartBody.Part.createFormData(name, filename, body.a());
    }
}
