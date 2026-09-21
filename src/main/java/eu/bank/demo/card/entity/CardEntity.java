package eu.bank.demo.card.entity;

import eu.bank.demo.card.enumeration.CardTypeEnum;
import eu.bank.demo.common.utils.AppConstantUtils;
import eu.bank.demo.customer.entity.CustomerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "CARD")
@RequiredArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @Enumerated(EnumType.STRING)
    private CardTypeEnum type;

    private String cardNumber;
    private LocalDateTime expiry;

    public String getSummary() {
        var cardNumber = this.getCardNumber();
        var cardNumberLastFourDigits = cardNumber.substring(cardNumber.length() - 4);

        return String.format(AppConstantUtils.HIDDEN_CARD_NUMBER, cardNumberLastFourDigits, this.getType().getLabel());
    }
}
