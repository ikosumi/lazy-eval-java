package lazyeval;

class Memoize<T> implements Lazy<T> {
    private Lazy<T> lazy;
    private T memo;

    public Memoize(Lazy<T> lazy) {
        this.lazy = lazy;
    }

    public T eval() {
        if (lazy != null) {
            memo = lazy.eval();
            lazy = null;
        }
        return memo;
    }
}
