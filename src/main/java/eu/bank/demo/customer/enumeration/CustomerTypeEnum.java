package eu.bank.demo.customer.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerTypeEnum {
    BUSINESS("Business"),
    PERSONAL("Personal");

    private final String label;
}
