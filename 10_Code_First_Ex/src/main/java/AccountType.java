public enum AccountType {
    FREE("FREE"),
    PLATINUM("PLATINUM"),
    GOLD("GOLD"),
    TRAIL("TRAIL");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
