package View.DefaultElement;

public class DropdownItem {

    private String key;
    private String value;

    public DropdownItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
