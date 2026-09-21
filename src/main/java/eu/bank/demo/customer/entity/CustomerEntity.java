package eu.bank.demo.customer.entity;

import eu.bank.demo.account.entity.AccountEntity;
import eu.bank.demo.card.entity.CardEntity;
import eu.bank.demo.customer.enumeration.CustomerTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Setter
@Getter
@RequiredArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum type;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CardEntity> cardList = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AccountEntity> accountList = new ArrayList<>();
}
