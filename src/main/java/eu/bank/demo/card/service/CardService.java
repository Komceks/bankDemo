package eu.bank.demo.card.service;

import eu.bank.demo.card.entity.CardEntity;
import eu.bank.demo.card.enumeration.CardTypeEnum;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    public List<CardEntity> filterByType(@NonNull List<CardEntity> entityList, @NonNull CardTypeEnum type) {
        return entityList.stream()
                .filter(cardEntity -> cardEntity.getType() == type)
                .toList();
    }
}
