public interface Payable {

    //принцип сегрегации (разделения) интерфейса, далее может быть не только кошелек, но и счет и т.п.

    public boolean pay(int sum);

    public void add(int sum);

    public int getBalance();
}
