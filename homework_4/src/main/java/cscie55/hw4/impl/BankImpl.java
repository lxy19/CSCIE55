package cscie55.hw4.impl;

import cscie55.hw4.api.Account;
import cscie55.hw4.api.Bank;
import cscie55.hw4.exception.DuplicateAccountException;
import cscie55.hw4.exception.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class BankImpl implements the interface Bank.
 * Added a private instance variable accounts, which used a hashMap. The key is the id of the account, and value is account.
 * Method addAccount(Account account): If an attempt is made to add an account with duplicate id, throws DuplicateAccountException. Add the account to the collection account.
 * Method transferWithoutLocking(int fromId, int toId, long amount): Withdraw on one account, and deposit on the other without synchronization.
 * Method transferLockingBank(int fromId, int toId, long amount): Transfer while synchronizing on the Bank object.
 * Method transferLockingAccounts(int fromId, int toId, long amount):This method does the transfer, locking just the two affected Accounts.
 */
public class BankImpl implements Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    /**
     * If an attempt is made to add an account with duplicate id, throws DuplicateAccountException.
     * Adds an Account to the Bank.
     * @param account the account added
     * @throws DuplicateAccountException
     */
    @Override
    public void addAccount(Account account) throws DuplicateAccountException {
        if (accounts.containsKey(account.getId()))
            throw new DuplicateAccountException(account.getId());
        accounts.put(account.getId(),account);
    }

    /**
     *Withdraw on one account, and deposit on the other without synchronization.
     * @param fromId id that transfer account from
     * @param toId id that transfer account to
     * @param amount amount of depoist
     * @throws InsufficientFundsException
     */
    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException {
        accounts.get(fromId).withdraw(amount);
        accounts.get(toId).deposit(amount);
    }

    /**
     * Transfer while synchronizing on the Bank object. Only one thread can do a transfer at any given moment.
     * @param fromId id that transfer account from
     * @param toId id that transfer account to
     * @param amount amount of depoist
     * @throws InsufficientFundsException
     */
    @Override
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException {
        synchronized (this){
            accounts.get(fromId).withdraw(amount);
            accounts.get(toId).deposit(amount);
        }
    }

    /**
     *This method does the transfer, locking just the two affected Accounts.
     * @param fromId id that transfer account from
     * @param toId id that transfer account to
     * @param amount amount of depoist
     * @throws InsufficientFundsException
     */
    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {
        Account fromAccount = accounts.get(fromId);
        Account toAccount = accounts.get(toId);
        synchronized (fromAccount) {
            fromAccount.withdraw(amount);
        }
        synchronized (toAccount) {
            toAccount.deposit(amount);
        }
    }

    /**
     * This method returns the total balances of account.
     * @return the total balances of account.
     */
    @Override
    public long getTotalBalances() {
        return accounts.values().stream().map(Account::getBalance).mapToLong(Long::longValue).sum();
    }

    @Override
    public int getNumberOfAccounts() {
        return accounts.size();
    }
}
