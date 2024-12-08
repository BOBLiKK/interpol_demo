package ehu.java.interpoldemo.model;

public enum UserRole {
    ADMIN("ADMIN"),
    GUEST("GUEST");

    private final String role;

    UserRole(String role) { this.role = role; }

    public String getUserRole() { return role; }

    public static UserRole getDefaultRole() { return GUEST; }
}

