package u2;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class a extends b {
    public final ArrayList<b> f4742a = new ArrayList<>();

    public a() {}

    public a(JSONArray array) {
        if (array == null) return;
        for (int i = 0; i < array.length(); i++) {
            Object v = array.opt(i);
            f4742a.add(e.wrap(v));
        }
    }

    @Override
    public String h() {
        return toString();
    }

    @Override
    public String toString() {
        JSONArray arr = new JSONArray();
        for (b item : f4742a) {
            if (item instanceof e) arr.put(((e) item).toJson());
            else if (item instanceof a) arr.put(((a) item).toJson());
            else if (item instanceof d) arr.put(org.json.JSONObject.NULL);
            else if (item instanceof c) arr.put(((c) item).raw());
            else arr.put(item == null ? org.json.JSONObject.NULL : item.h());
        }
        return arr.toString();
    }

    JSONArray toJson() {
        try {
            return new JSONArray(toString());
        } catch (Exception unused) {
            return new JSONArray();
        }
    }
}
