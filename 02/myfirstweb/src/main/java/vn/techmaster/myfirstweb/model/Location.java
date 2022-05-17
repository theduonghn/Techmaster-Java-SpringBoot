package vn.techmaster.myfirstweb.model;

public enum Location {
    HA_NOI("Hanoi"), HAI_PHONG("Hai Phong"),
    DA_NANG("Da Nang"), HO_CHI_MINH("Ho Chi Minh");

    private String value;

    private Location(String value) {
        this.value = value;
    }
}
