package com.project.ATM_Interface.com.Repository;

import com.project.ATM_Interface.com.Model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByAccountNumber(String accountNumber);

}
