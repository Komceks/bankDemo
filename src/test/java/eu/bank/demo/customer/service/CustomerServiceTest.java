package eu.bank.demo.customer.service;

import eu.bank.demo.card.entity.CardEntity;
import eu.bank.demo.card.enumeration.CardTypeEnum;
import eu.bank.demo.card.service.CardService;
import eu.bank.demo.customer.entity.CustomerEntity;
import eu.bank.demo.customer.enumeration.CustomerTypeEnum;
import eu.bank.demo.customer.exception.CustomerNotFoundException;
import eu.bank.demo.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static eu.bank.demo.customer.exception.CustomerNotFoundException.CUSTOMER_NOT_FOUND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @Mock
    CardService cardService;

    @InjectMocks
    CustomerService customerService;

    @Test
    void givenBusinessType_whenGetCustomer_thenReturnCustomerWithoutCreditCard() {
        CustomerEntity root = new CustomerEntity();
        root.setId(1L);
        root.setType(CustomerTypeEnum.BUSINESS);
        root.setFullName("John Doe");

        CardEntity debitCard = new CardEntity();
        debitCard.setId(3L);
        debitCard.setType(CardTypeEnum.DEBIT);
        debitCard.setCardNumber("1254 2538 8965 1245");

        CardEntity creditCard = new CardEntity();
        creditCard.setId(2L);
        creditCard.setType(CardTypeEnum.CREDIT);
        creditCard.setCardNumber("7841 2345 8912 7452");

        root.setCardList(List.of(debitCard, creditCard));
        root.setAccountList(List.of());

        when(customerRepository.findByIdWithCardAndAccountFetched(1L)).thenReturn(root);
        when(cardService.filterByType(root.getCardList(), CardTypeEnum.DEBIT)).thenReturn(List.of(debitCard));

        var customerDetails = customerService.getCustomerDetails(1L);

        assertThat(customerDetails.getCards()).hasSize(1);
        assertThat(customerDetails.getCards().getFirst().getId()).isEqualTo(3L);

        verify(cardService).filterByType(root.getCardList(), CardTypeEnum.DEBIT);
        verify(customerRepository).findByIdWithCardAndAccountFetched(1L);
    }

    @Test
    void givenPersonalType_whenGetCustomer_thenReturnCustomerWithAllTypeCards() {
        CustomerEntity root = new CustomerEntity();
        root.setId(1L);
        root.setType(CustomerTypeEnum.PERSONAL);
        root.setFullName("John Doe");

        CardEntity debitCard = new CardEntity();
        debitCard.setId(3L);
        debitCard.setType(CardTypeEnum.DEBIT);
        debitCard.setCardNumber("1254 2538 8965 1245");

        CardEntity creditCard = new CardEntity();
        creditCard.setId(2L);
        creditCard.setType(CardTypeEnum.CREDIT);
        creditCard.setCardNumber("7841 2345 8912 7452");

        root.setCardList(List.of(debitCard, creditCard));
        root.setAccountList(List.of());

        when(customerRepository.findByIdWithCardAndAccountFetched(1L)).thenReturn(root);

        var customerDetails = customerService.getCustomerDetails(1L);

        assertThat(customerDetails.getCards()).hasSize(2);

        verify(customerRepository).findByIdWithCardAndAccountFetched(1L);
    }

    @Test
    void givenNonExistingId_whenGetCustomer_thenThrowCustomerNotFoundException() {
        when(customerRepository.findByIdWithCardAndAccountFetched(1L)).thenReturn(null);

        assertThatThrownBy(() -> customerService.getCustomerDetails(1L))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessage(String.format(CUSTOMER_NOT_FOUND_MESSAGE, 1));

        verify(customerRepository).findByIdWithCardAndAccountFetched(1L);
        verifyNoInteractions(cardService);
    }
}
