public class ArrayPriorityQueue {
    private Event[] A;
    private int n;

    public ArrayPriorityQueue(int capacity) {
        this.A = new Event[capacity+1];
        this.n = 0;
    }

    public int size() {
        return this.n;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public Event getMin() {
        if (this.isEmpty()) {
            return null;
        }
        return this.A[1];
    }

    public void insert(Event e) {
        A[++n] = e;
        int child = n;
        int parent = parentIndex(child);
        while (parent >= 1 && A[child].compareTo(A[parent]) < 0) {
            swap(child, parent);
            child = parent;
            parent = parentIndex(child);
        }
    }

    public Event extractMin() {
        if (this.isEmpty()) {
            return null;
        }
        swap(1, n);
        Event min = A[n];
        A[n] = null;
        n--;
        int parent = 1;
        while (true) {
            int left = leftChildIndex(parent);
            int right = rightChildIndex(parent);
            int smallest = parent;
            if (left <= n && A[left].compareTo(A[smallest]) < 0) {
                smallest = left;
            }
            if (right <= n && A[right].compareTo(A[smallest]) < 0) {
                smallest = right;
            }
            if (smallest == parent) break;
            swap(parent, smallest);
            parent = smallest;
        }
        return min;
    }

    private int parentIndex(int i) {
        return i / 2;
    }

    private int leftChildIndex(int i) {
        return 2 * i;
    }

    private int rightChildIndex(int i) {
        return 2 * i + 1;
    }

    private void swap(int i, int j) {
        Event temp = this.A[i];
        this.A[i] = this.A[j];
        this.A[j] = temp;
    }

    private boolean hasSmallerChild(int i) {
        int lC = leftChildIndex(i);
        int rC = rightChildIndex(i);
        if (lC <= this.n && this.A[lC].compareTo(this.A[i]) < 0) {
            return true;
        }
        if (rC <= this.n && this.A[rC].compareTo(this.A[i]) < 0) {
            return true;
        }
        return false;
    }
}