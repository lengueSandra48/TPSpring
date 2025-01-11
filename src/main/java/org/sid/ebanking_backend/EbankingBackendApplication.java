package org.sid.ebanking_backend;

import org.sid.ebanking_backend.entities.*;
import org.sid.ebanking_backend.exceptions.BalanceInsufficientException;
import org.sid.ebanking_backend.exceptions.BankingAccoundNotFoundException;
import org.sid.ebanking_backend.exceptions.CustomerNotFoundException;
import org.sid.ebanking_backend.services.IBankAccounService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

	public static void main(String[] args) {SpringApplication.run(EbankingBackendApplication.class, args);}

	@Bean
	CommandLineRunner commandLineRunner(IBankAccounService bankAccounService){

        return args -> {
			Stream.of("Lengue", "Zepoh", "Tekson").forEach(name ->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");

				bankAccounService.saveCustomer(customer);

			});

			bankAccounService.listCustomers().forEach(customer -> {
                try {
                    bankAccounService.saveCurrentBankAccount(Math.random()*90000, 90000, customer.getId());
					bankAccounService.saveSavingBankAccount(Math.random()*1000000, 100000, customer.getId());
					List<BankAccount> bankAccounts = bankAccounService.bankAccountList();
					for (BankAccount bankAccount:bankAccounts){
						for (int i=0; i < 10; i++){
							bankAccounService.credit(bankAccount.getId(),100000+Math.random()*120000, "Credit");
							bankAccounService.debit(bankAccount.getId(), 1000+Math.random()*90000, "Debit");
						}
					}
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				} catch (BankingAccoundNotFoundException | BalanceInsufficientException e) {
                   e.printStackTrace();
                }

            });

		};
    }

}
