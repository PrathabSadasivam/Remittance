package com.bsfdv.remittance.dao.repository;

import com.bsfdv.remittance.dao.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;


@Repository
public interface AccountsRepository extends JpaRepository<Account, Long>{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Account a where a.accountId = :id")
    Account updateAccountBalace(@Param("id") long id);
}
