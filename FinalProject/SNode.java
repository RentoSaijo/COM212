public class SNode {
    private String element;
    private SNode next;

    public SNode(String element, SNode next) {
        this.element = element;
        this.next = next;
    }

    public String getElement() {
        return this.element;
    }

    public SNode getNext() {
        return this.next;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setNext(SNode next) {
        this.next = next;
    }

    public String toString() {
        return this.element + "";
    }
}