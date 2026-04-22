package u2;

import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class e extends b {
    public final Map<String, b> f4744a = new LinkedHashMap<>();

    public e() {}

    public e(JSONObject obj) {
        if (obj == null) return;
        for (String key : obj.keySet()) {
            f4744a.put(key, wrap(obj.opt(key)));
        }
    }

    static b wrap(Object value) {
        if (value == null || value == JSONObject.NULL) return new d();
        if (value instanceof JSONObject) return new e((JSONObject) value);
        if (value instanceof JSONArray) return new a((JSONArray) value);
        if (value instanceof Boolean || value instanceof Number || value instanceof String) return new c(value);
        return new c(String.valueOf(value));
    }

    public void i(String key, String value) {
        f4744a.put(key, new c(value));
    }

    public boolean k(String key) {
        return f4744a.containsKey(key) && !(f4744a.get(key) instanceof d);
    }

    public b j(String key) {
        b value = f4744a.get(key);
        return value == null ? new d() : value;
    }

    @Override
    public e g() {
        return this;
    }

    JSONObject toJson() {
        JSONObject obj = new JSONObject();
        for (Map.Entry<String, b> entry : f4744a.entrySet()) {
            b value = entry.getValue();
            if (value instanceof e) obj.put(entry.getKey(), ((e) value).toJson());
            else if (value instanceof a) obj.put(entry.getKey(), ((a) value).toJson());
            else if (value instanceof d) obj.put(entry.getKey(), JSONObject.NULL);
            else if (value instanceof c) obj.put(entry.getKey(), ((c) value).raw());
            else obj.put(entry.getKey(), value == null ? JSONObject.NULL : value.h());
        }
        return obj;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }

    @Override
    public String h() {
        return toString();
    }
}
