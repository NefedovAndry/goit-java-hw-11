package numbers_equivalent;

class ThreadA implements Runnable {
    final NumberToString input;

    public ThreadA(NumberToString input) {
        this.input = input;
    }

    @Override
    public void run() {
        while (!input.isDone) {
            input.fizz();
            if (input.isBuzz) {
                synchronized (input) {
                    notifyAll();
                }
            }
            synchronized (input.newIteration) {
                while (input.isNewIteration) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
