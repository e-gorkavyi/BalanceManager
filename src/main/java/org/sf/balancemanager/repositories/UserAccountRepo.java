package org.sf.balancemanager.repositories;

import org.sf.balancemanager.entities.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Long> {

}
