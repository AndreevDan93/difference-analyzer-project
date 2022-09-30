package hexlet.code;

public enum Status {
    STATUS_UNCHANGED("status:unchanged"),
    STATUS_CHANGED("status:changed"),
    STATUS_ADDED("status:added"),
    STATUS_DELETED("status:deleted");

    private final String value;

    Status(String title) {
        this.value = title;
    }

    public String getValue() {
        return value;
    }
}
