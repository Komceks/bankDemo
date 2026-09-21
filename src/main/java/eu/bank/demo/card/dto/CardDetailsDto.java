package eu.bank.demo.card.dto;

import eu.bank.demo.card.entity.CardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class CardDetailsDto {
    private Long id;
    private String value;

    public static CardDetailsDto of(@NonNull CardEntity cardEntity) {
        return CardDetailsDto.builder()
                .id(cardEntity.getId())
                .value(cardEntity.getSummary())
                .build();
    }

    public static List<CardDetailsDto> listOf(@NonNull List<CardEntity> cardEntityList) {
        return cardEntityList.stream()
                .map(CardDetailsDto::of)
                .collect(Collectors.toList());
    }
}
