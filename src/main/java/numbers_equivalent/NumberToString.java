package numbers_equivalent;

import java.util.Objects;

class NumberToString {
    Integer value;
    Integer bufferValue;
    boolean isFizz = false;
    boolean isBuzz = false;
    boolean isFizzBuzz = false;
    boolean isNewIteration = false;
    boolean isDone;
    StringBuffer result;
    final Object newIteration = new Object();

    public NumberToString(int value) {
        this.value = value;
        this.bufferValue = 1;
        this.result = new StringBuffer(bufferValue.toString());
        isDone = false;
    }

    public void iterator() {
        this.result = new StringBuffer(bufferValue.toString());
        bufferValue++;
        isFizz = false;
        isBuzz = false;
        isFizzBuzz = false;
        isNewIteration = true;
    }

    public void fizz() {
        isNewIteration = false;
        if (bufferValue % 3 == 0) {
            this.result = new StringBuffer("fizz");
        }
        isFizz = true;
    }

    public void buzz() {
        isNewIteration = false;
        if (bufferValue % 5 == 0) {
            this.result = new StringBuffer("buzz");
        }
        isBuzz = true;
    }

    public void fizzbuzz() {
        if (bufferValue % 3 == 0 && bufferValue % 5 == 0) {
            this.result = new StringBuffer("fizzbuzz");
        }
        isFizzBuzz = true;
    }

    public void number() {
        if (!Objects.equals(bufferValue, value)) {
            result.append(", ");
            System.out.print(result);
            iterator();
        } else {
            System.out.println(result);
            isDone = true;
        }
    }
}
