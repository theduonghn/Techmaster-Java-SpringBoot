package vn.techmaster.myfirstweb.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Location {
    HA_NOI("Hanoi"),
    HAI_PHONG("Hai Phong"),
    DA_NANG("Da Nang"),
    HO_CHI_MINH("Ho Chi Minh");

    @JsonValue
    private String value;

    @JsonCreator
    public static Location fromString(String value) {
        for (Location status : Location.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}
