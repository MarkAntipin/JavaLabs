public class Salary implements Comparable<Salary>{
    private int min;
    private int max;
    public Salary(int min, int max) {
        this.min = min;
        this.max = max;
    }

    int getMin() {
        return min;
    }

    int getMax() {
        return min;
    }

    int getMiddle() {
        return (this.getMin() + this.getMax()) / 2;
    }

    @Override
    public String toString() {
        return String.format(min + " - " + max);
    }

    @Override
    public int compareTo(Salary s) {
        return Integer.compare(this.getMiddle(), (s.getMiddle()));
    }
}
