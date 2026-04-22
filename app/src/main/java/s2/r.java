package s2;

import org.json.JSONArray;
import org.json.JSONObject;
import u2.a;
import u2.b;
import u2.d;
import u2.e;

public final class r {
    private r() {}

    public static e d1(String json) {
        try {
            String trimmed = json == null ? "" : json.trim();
            if (trimmed.startsWith("[")) {
                JSONArray arr = new JSONArray(trimmed);
                e wrapper = new e();
                wrapper.i("data", new a(arr));
                return wrapper;
            }
            return new e(new JSONObject(trimmed.isEmpty() ? "{}" : trimmed));
        } catch (Exception e2) {
            return new e();
        }
    }

    public static void r(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }
}
