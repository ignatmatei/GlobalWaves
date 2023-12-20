package app.user;

public final class NormalUser extends User {
    public NormalUser(final String username, final int age, final String city) {
        super(username, age, city);
        super.setPage("Home");
    }
}
