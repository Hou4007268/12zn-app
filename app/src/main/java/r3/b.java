package r3;

import java.util.concurrent.TimeUnit;

public final class b {
    private b() {}

    public static long b(long value, TimeUnit unit) {
        return unit.toMillis(value);
    }
}
