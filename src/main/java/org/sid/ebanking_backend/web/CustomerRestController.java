package org.sid.ebanking_backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking_backend.dtos.CustomerDTO;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;
import org.sid.ebanking_backend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor  //Injection des dependances par le constructeur
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){

        return bankAccountService.listCustomers();
    }

    @PostMapping("/savedCustomer")
    private CustomerDTO createCustomer(@RequestBody CustomerDTO customerRequest) {

        return bankAccountService.saveCustomer(customerRequest);
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException {
      return bankAccountService.findCustomerById(id); // Retourne le clientdto
    }

    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable("id") Long customerId,@RequestBody CustomerDTO customerDTORequest){
        customerDTORequest.setId(customerId);

        return bankAccountService.updateCustomer(customerDTORequest);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        bankAccountService.delete(id);
    }


}
