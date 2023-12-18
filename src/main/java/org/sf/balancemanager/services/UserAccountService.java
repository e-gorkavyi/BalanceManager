package org.sf.balancemanager.services;

import org.sf.balancemanager.entities.Operation;
import org.sf.balancemanager.entities.OperationType;
import org.sf.balancemanager.entities.UserAccount;
import org.sf.balancemanager.repositories.OperationRepo;
import org.sf.balancemanager.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAccountService {

    private final UserAccountRepo userAccountRepo;
    private final OperationRepo operationRepo;

    @Autowired
    public UserAccountService(UserAccountRepo userAccountRepo, OperationRepo operationRepo) {
        this.userAccountRepo = userAccountRepo;
        this.operationRepo = operationRepo;
    }

    public Double getBalance(Long userAccountId) throws NoSuchFieldException {
        UserAccount userAccount = userAccountRepo
                .findById(userAccountId)
                .orElseThrow(() -> new NoSuchFieldException("Пользователь не найден"));
        return userAccount.getBalance().doubleValue() / 100;
    }

    @Transactional
    public void takeMoney(Long userAccountId, Double amount) throws NoSuchFieldException, IllegalArgumentException {
        UserAccount userAccount = userAccountRepo
                .findById(userAccountId)
                .orElseThrow(() -> new NoSuchFieldException("Пользователь не найден"));

        if (amount * 100 > userAccount.getBalance().doubleValue())
            throw new IllegalArgumentException("Сумма превышает остаток на счёте");

        userAccount.setBalance((long) (userAccount.getBalance() - amount * 100));
        userAccountRepo.save(userAccount);

        Operation operation = new Operation(null, null, OperationType.WITHDRAWAL, (long) (amount * 100), userAccountId);
        operationRepo.save(operation);
    }

    @Transactional
    public void putMoney(Long userAccountId, Double amount) throws NoSuchFieldException {
        UserAccount userAccount = userAccountRepo
                .findById(userAccountId)
                .orElseThrow(() -> new NoSuchFieldException("Пользователь не найден"));

        userAccount.setBalance((long) (userAccount.getBalance() + amount * 100));
        userAccountRepo.save(userAccount);

        Operation operation = new Operation(null, null, OperationType.REFILL, (long) (amount * 100), userAccountId);
        operationRepo.save(operation);
    }

    @Transactional
    public void transferMoney(Long userAccountId1,
                              Long userAccountId2,
                              Double amount) throws NoSuchFieldException, IllegalArgumentException {
        UserAccount userAccount1 = userAccountRepo
                .findById(userAccountId1)
                .orElseThrow(() -> new NoSuchFieldException("Отправитель не найден."));
        UserAccount userAccount2 = userAccountRepo
                .findById(userAccountId2)
                .orElseThrow(() -> new NoSuchFieldException("Получатель не найден."));
        if (amount * 100 > userAccount1.getBalance().doubleValue())
            throw new IllegalArgumentException("Сумма превышает остаток на счёте");

        userAccount1.setBalance((long) (userAccount1.getBalance() - amount * 100));
        userAccount2.setBalance((long) (userAccount2.getBalance() + amount * 100));

        userAccountRepo.save(userAccount1);
        userAccountRepo.save(userAccount2);

        Operation operation1 = new Operation(null, null, OperationType.OUTGOING_TRANSFER, (long) (amount * 100), userAccountId1);
        Operation operation2 = new Operation(null, null, OperationType.INCOMING_TRANSFER, (long) (amount * 100), userAccountId2);
        operationRepo.save(operation1);
        operationRepo.save(operation2);
    }
}
