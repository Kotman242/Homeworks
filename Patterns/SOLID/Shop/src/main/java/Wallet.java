public class Wallet implements Payable {

    // принцип единственной ответственности  - класс выполняет только функции кошелька покупателя.

    private int balance;

    public void add(int sum) {
        balance += sum;
    }

    public boolean pay(int sum) {
        if (balance >= sum) {
            balance -= sum;
            return true;
        } else {
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }
}
