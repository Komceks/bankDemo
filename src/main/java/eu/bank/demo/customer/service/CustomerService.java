package eu.bank.demo.customer.service;

import eu.bank.demo.card.enumeration.CardTypeEnum;
import eu.bank.demo.card.service.CardService;
import eu.bank.demo.customer.controller.dto.CustomerDetailsDto;
import eu.bank.demo.customer.enumeration.CustomerTypeEnum;
import eu.bank.demo.customer.exception.CustomerNotFoundException;
import eu.bank.demo.customer.repository.CustomerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CardService cardService;

    public CustomerDetailsDto getCustomerDetails(@NonNull Long id) {
        var customerEntity = customerRepository.findByIdWithCardAndAccountFetched(id);

        if (customerEntity == null) {
            throw new CustomerNotFoundException(id);
        }

        if (CustomerTypeEnum.BUSINESS == customerEntity.getType()) {
            var debitCardEntityList = cardService.filterByType(customerEntity.getCardList(), CardTypeEnum.DEBIT);
            return CustomerDetailsDto.of(customerEntity, debitCardEntityList);
        }

        return CustomerDetailsDto.of(customerEntity, customerEntity.getCardList());
    }
}
