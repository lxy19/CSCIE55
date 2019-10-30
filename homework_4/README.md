Homework 4
CSCIE-55
Fall 2019
Xiangye Li

Last Modified: Saturday, 27-Oct-2019 11:20:58 EDT
Implementation
There are several new types of classes:
Class AccountImpl implements Account interface.
  -Method deposit:  Deposit amount to the account. If amount<0, throw IllegalArgumentException.
  -Method withdraw: If an attempt to withdraw more than the balance, then throw InsufficientFundsException and
   the balances of accounts must be left. If withraw amount is less than 0, throw IllegalArgumentException.
  -Method equals: Make sure no duplicate ids for 2 different accounts. This method is used when check if the account contains
   in a collection.

Class BankImpl implements the interface Bank.
  Private instance variable:
  -accounts, which used a hashMap. The key is the id of the account, and value is account.
  Methods:
  -Method addAccount(Account account): If an attempt is made to add an account with duplicate id, throws DuplicateAccountException. Add the account to the collection account.
  -Method transferWithoutLocking(int fromId, int toId, long amount): Withdraw on one account, and deposit on the other without synchronization.
  -Method transferLockingBank(int fromId, int toId, long amount): Transfer while synchronizing on the Bank object.
  -Method transferLockingAccounts(int fromId, int toId, long amount):This method does the transfer, locking just the two affected Accounts.
 
 To run the program from the jar file:
 java -jar li_xiangye_hw4-1.0-SNAPSHOT-tests.jar cscie55.hw4.ThreadsTest

Results:
JUnit version 4.11
.NO_LOCKING, 1 -- OK: 5457.495280 transactions/msec
NO_LOCKING, 2 -- BROKEN: 8385.150770 transactions/msec  Expected total balances: 100000 Actual: 107289
NO_LOCKING, 5 -- BROKEN: 20505.779144 transactions/msec Expected total balances: 100000 Actual: 198093
NO_LOCKING, 10 -- BROKEN: 31030.401726 transactions/msec        Expected total balances: 100000 Actual: 351654
NO_LOCKING, 20 -- BROKEN: 39437.619545 transactions/msec        Expected total balances: 100000 Actual: 584253
LOCK_BANK, 1 -- OK: 5074.962268 transactions/msec
LOCK_BANK, 2 -- OK: 2156.546096 transactions/msec
LOCK_BANK, 5 -- OK: 1871.738449 transactions/msec
LOCK_BANK, 10 -- OK: 1936.215486 transactions/msec
LOCK_BANK, 20 -- OK: 1896.886610 transactions/msec
LOCK_ACCOUNTS, 1 -- OK: 4133.873338 transactions/msec
LOCK_ACCOUNTS, 2 -- OK: 6217.156317 transactions/msec
LOCK_ACCOUNTS, 5 -- OK: 6335.782536 transactions/msec
LOCK_ACCOUNTS, 10 -- OK: 7840.224996 transactions/msec
LOCK_ACCOUNTS, 20 -- OK: 11030.787369 transactions/msec
........
Time: 20.792
OK (9 tests)
 
Generated Jar files:
li_xiangye_hw4-1.0-SNAPSHOT-tests.jar: executable jar file to run the program
li_xiangye_hw4-1.0-SNAPSHOT.jar: a snap shot version of the jar file
li_xiangye_hw4-1.0-SNAPSHOT-sources.jar: all the java source files
 