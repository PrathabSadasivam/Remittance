package com.bsfdv.remittance.dao.repository;

import com.bsfdv.remittance.dao.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<AccountHolder, Long>{
    @Query("select a from AccountHolder a where a.userName = :userName")
   public AccountHolder findUserNameByName( String userName);
}
