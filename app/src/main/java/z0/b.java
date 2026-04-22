package z0;

import android.content.Context;
import android.content.SharedPreferences;

public final class b {
    private b() {}

    public static SharedPreferences a(Object ignored, Context context) {
        return context.getSharedPreferences("user", 0);
    }
}
