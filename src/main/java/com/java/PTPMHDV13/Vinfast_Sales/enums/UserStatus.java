package com.java.PTPMHDV13.Vinfast_Sales.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    INACTIVE(0),
    ACTIVE(1);

    private final int value;

    UserStatus(int value) {
        this.value = value;
    }
}
