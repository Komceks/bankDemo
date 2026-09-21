package eu.bank.demo.customer.controller;

import eu.bank.demo.common.utils.RestControllerConstantUtils;
import eu.bank.demo.customer.controller.dto.CustomerDetailsDto;
import eu.bank.demo.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestControllerConstantUtils.CUSTOMER_CONTROLLER_BASE_PATH)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public CustomerDetailsDto getCustomerDetails(@PathVariable Long id) {
        return customerService.getCustomerDetails(id);
    }
}
