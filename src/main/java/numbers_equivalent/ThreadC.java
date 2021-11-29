package numbers_equivalent;

class ThreadC implements Runnable {
    final NumberToString input;

    public ThreadC(NumberToString input) {
        this.input = input;
    }

    @Override
    public void run() {
        while (!input.isDone) {
            synchronized (input) {
                while (input.isFizz && input.isBuzz) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.fizzbuzz();
            synchronized (input) {
                notifyAll();
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
