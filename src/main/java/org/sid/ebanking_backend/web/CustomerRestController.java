package org.sid.ebanking_backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking_backend.entities.Customer;
import org.sid.ebanking_backend.services.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor  //Injection des dependances par le constructeur
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
    public List<Customer> customers(){

        return bankAccountService.listCustomers();
    }

    @PostMapping("/savedCustomer")
    private ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucb) {
        // Sauvegarde du client
        Customer savedCustomer = bankAccountService.saveCustomer(customer);

        // Construction de l'URI de la ressource créée
        URI location = ucb
                .path("/savedCustomer")
                .buildAndExpand(savedCustomer.getId())
                .toUri();

        // Retourne une réponse HTTP 201 Created avec l'URI dans l'en-tête Location
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id) {
        // Appel au service pour récupérer le client
        Optional<Customer> customer = bankAccountService.findCustomerById(id);

        if (customer.isPresent()) {
            return ResponseEntity.ok(customer); // Retourne le client avec le statut 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Retourne le statut 404 Not Found si le client n'existe pas
        }
    }


}
