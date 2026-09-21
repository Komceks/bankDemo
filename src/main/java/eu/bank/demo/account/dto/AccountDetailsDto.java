package eu.bank.demo.account.dto;

import eu.bank.demo.account.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AccountDetailsDto {
    private Long id;
    private String value;

    public static AccountDetailsDto of(@NonNull AccountEntity accountEntity) {
        return AccountDetailsDto.builder()
                .id(accountEntity.getId())
                .value(accountEntity.getSummary())
                .build();
    }

    public static List<AccountDetailsDto> listOf(@NonNull List<AccountEntity> accountEntityList) {
        return accountEntityList.stream()
                .map(AccountDetailsDto::of)
                .collect(Collectors.toList());
    }
}
