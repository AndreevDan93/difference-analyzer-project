package hexlet.code;

public final class Value {
    public static final String IS_NOT_INITIALIZED = "isNotInitialized";
    private final Object firstOb;
    private final Object secondOb;
    private final Status status;


    public Value(Object firstOb, Object secondOb) {
        this.firstOb = firstOb;
        this.secondOb = secondOb;
        this.status = identifyStatus();
    }

    public Object getFirstOb() {
        return this.firstOb;
    }

    public Object getSecondOb() {
        return this.secondOb;
    }

    public Status getStatus() {
        return this.status;
    }

    private Status identifyStatus() {
        if (this.firstOb == null || this.secondOb == null) {
            return (this.firstOb == null && this.secondOb == null) ? Status.STATUS_UNCHANGED : Status.STATUS_CHANGED;
        } else if (this.firstOb.equals(IS_NOT_INITIALIZED)) {
            return Status.STATUS_ADDED;
        } else if (this.secondOb.equals(IS_NOT_INITIALIZED)) {
            return Status.STATUS_DELETED;
        } else {
            return (this.firstOb.equals(this.secondOb)) ? Status.STATUS_UNCHANGED : Status.STATUS_CHANGED;
        }
    }
}
