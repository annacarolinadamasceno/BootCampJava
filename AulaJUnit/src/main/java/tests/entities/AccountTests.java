package tests.entities;

import entities.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.factory.AccountFactory;

public class AccountTests {


    @Test
    public void depositShouldIncreaseBalanceWhenPositiveAmount(){
        //Arrange
        double amount = 200.0;
        double expectedValue = 196.0;
        Account acc = AccountFactory.createEmptyAccount();
        //Act
        acc.deposit(amount);
        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void depositShouldDoNothingWhenNegativeAmount(){
        //Arrange
        double amount = -200.00;
        double expectedValue = 100.0;
        Account acc = AccountFactory.createAccount(expectedValue);
        //Act
        acc.deposit(amount);
        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void fullWithdrawShouldClearBalanceAndReturnBalance(){
        //Arrange
        double balance = 100;
        double expectedValue = 0.0;
        Account acc = AccountFactory.createAccount(balance);
        //Act
        double result = acc.fullWithdraw();
        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
        Assertions.assertTrue(result == balance); //test the return
    }

    @Test
    public void withdrawShouldDecreaseBalanceWhenBalanceIsBiggerThenAmount(){
        //Arrange
        double amount = 200.0;
        double initialBalance = 300.0;
        double expectedValue = 100.0;
        Account acc = AccountFactory.createAccount(initialBalance);
        //Act
        acc.withdraw(amount);
        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void withdrawShouldThrowExceptionWhenAmountIsBiggerThenBalance(){
        //Arrange
        double amount = 400.0;
        double initialBalance = 300.0;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Account acc = AccountFactory.createAccount(initialBalance);
            acc.withdraw(amount);
        });
    }
}
