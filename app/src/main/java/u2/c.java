package u2;

final class c extends b {
    private final Object value;

    c(Object value) {
        this.value = value;
    }

    @Override
    public String h() {
        return value == null ? "" : String.valueOf(value);
    }

    @Override
    public boolean f() {
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return value != null && Boolean.parseBoolean(String.valueOf(value));
    }

    Object raw() {
        return value;
    }
}
