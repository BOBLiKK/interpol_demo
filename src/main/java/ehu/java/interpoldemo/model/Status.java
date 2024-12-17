package ehu.java.interpoldemo.model;

public enum Status {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String status;

    Status(String status) {this.status = status;}
    public String getStatus() {return status;}

    public static Status getDefaultStatus() {
        return PENDING;
    }
}
