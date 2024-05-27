public interface Strategy {
    // 投资方法，根据公司当前的表现确定投资多少股票
    double invest(Company company);
}
