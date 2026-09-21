package eu.bank.demo.card.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CardTypeEnum {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String label;
}
