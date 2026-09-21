package eu.bank.demo.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.bank.demo.account.dto.AccountDetailsDto;
import eu.bank.demo.card.dto.CardDetailsDto;
import eu.bank.demo.card.entity.CardEntity;
import eu.bank.demo.customer.entity.CustomerEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomerDetailsDto {
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    private String type;
    private List<CardDetailsDto> cards;
    private List<AccountDetailsDto> accounts;

    public static CustomerDetailsDto of(CustomerEntity customerEntity, List<CardEntity> cardEntityList) {
        if (customerEntity == null) {
            return CustomerDetailsDto.builder().build();
        }

        return CustomerDetailsDto.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .type(customerEntity.getType().getLabel())
                .cards(CardDetailsDto.listOf(cardEntityList))
                .accounts(AccountDetailsDto.listOf(customerEntity.getAccountList()))
                .build();
    }
}
