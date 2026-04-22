package q3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class w {
    public final List<MultipartBody.Part> f4186c = new ArrayList<>();
    private final Map<String, String> fields = new LinkedHashMap<>();
    private MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

    public void a(String name, String value) {
        fields.put(name, value);
    }

    public void c(y yVar) {
        builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    }

    public y b() {
        MultipartBody.Builder b = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            b.addFormDataPart(entry.getKey(), entry.getValue());
        }
        for (MultipartBody.Part part : f4186c) {
            b.addPart(part);
        }
        return new y(b.build());
    }
}
