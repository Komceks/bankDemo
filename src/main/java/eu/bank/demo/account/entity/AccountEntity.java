package eu.bank.demo.account.entity;

import eu.bank.demo.common.utils.AppConstantUtils;
import eu.bank.demo.customer.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Setter
@Getter
@Table(name = "ACCOUNT")
@RequiredArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    private String iban;
    private Currency currency;
    private BigDecimal balance;

    public String getSummary() {
        return String.format(AppConstantUtils.ACCOUNT_SUMMARY_FORMAT, this.getIban(), this.getBalance(), this.getCurrency().getCurrencyCode());
    }
}
