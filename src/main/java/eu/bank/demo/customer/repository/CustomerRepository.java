package eu.bank.demo.customer.repository;

import eu.bank.demo.customer.entity.CustomerEntity;
import eu.bank.demo.customer.repository.custom.CustomCustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, CustomCustomerRepository {
}
