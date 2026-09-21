package eu.bank.demo.customer.repository.custom;

import eu.bank.demo.customer.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomCustomerRepository {
    CustomerEntity findByIdWithCardAndAccountFetched(Long id);
}
