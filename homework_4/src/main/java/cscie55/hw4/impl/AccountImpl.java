package cscie55.hw4.impl;

import cscie55.hw4.api.Account;
import cscie55.hw4.exception.InsufficientFundsException;

/**
 * This class AccountImpl implements Account inteface.
 * Method deposit:  Deposit amount to the account. If amount<0, throw IllegalArgumentException.
 * Method withdraw: If an attempt to withdraw more than the balance, then throw InsufficientFundsException and
 * the balances of accounts must be left. If withraw amount less than 0, throw IllegalArgumentException.
 * Method equals: Make sure no duplicate ids for 2 different accounts. This method is used when check if the account contains
 * in a collection.
 */
public class AccountImpl implements Account {
    private long amount;
    private int id;

    public AccountImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public long getBalance() {
        return this.amount;
    }

    /**
     * If amount to deposit is less than 0, throw IllegalArgumentException.
     * Deposit amount to the account.
     * @param amount
     */
    @Override
    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Illegal amount to withdraw");
        }
        this.amount += amount;
    }

    /**
     * Withdraw amount from the account.
     * The Bank transfer methods throw InsufficientFundsException on an attempt to withdraw more than the balance.
     * If this happens, then the balances of both accounts must be left .
     * If withraw amount less than 0, throw IllegalArgumentException.
     * @param amount
     * @throws InsufficientFundsException
     */
    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Illegal amount to withdraw");
        }
        if (this.amount - amount < 0) {
            throw new InsufficientFundsException(this, amount);
        }
        this.amount -= amount;
    }

    /**
     * This is to make sure no duplicate ids for 2 different accounts.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountImpl account = (AccountImpl) o;
        return id == account.id;
    }

}
