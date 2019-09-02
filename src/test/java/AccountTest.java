import acount.Account;
import acount.Card;
import acount.Customer;
import functions.UncheckedFunction2;
import monad.Just;
import monad.Value;

public class AccountTest {

  public static final UncheckedFunction2<Value<Double>, Value<Double>, Value<Boolean>> GREATER = Value.lift(AccountTest::greater);

  public static void main(String[] args) {
  }

  Value<Boolean> owesMoney(Customer customer) throws Exception {
    return GREATER.apply(
        Just.of(customer)
            .map(Customer::getAccount)
            .map(Account::getDebitCard)
            .map(Card::getAmount),
        Just.of(customer)
            .map(Customer::getAccount)
            .map(Account::getCreditCard)
            .map(Card::getAmount)
    );
  }

  private static boolean greater(double x, double y) {
    return x > y;
  }

}
