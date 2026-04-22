package q3;

import java.io.File;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class e0 extends f0 {
    public final File file;

    public e0(File file, MediaType mediaType) {
        super(RequestBody.create(file, mediaType));
        this.file = file;
    }
}
