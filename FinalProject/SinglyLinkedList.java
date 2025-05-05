public class SinglyLinkedList {
    private SNode head;
    private SNode tail;
    int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public SNode getHead() {
        return this.head;
    }

    public SNode getTail() {
        return this.tail;
    }

    public String first() {
        if (this.isEmpty()) {
            return null;
        }
        return this.head.getElement();
    }

    public String last() {
        if (this.isEmpty()) {
            return null;
        }
        return this.tail.getElement();
    }

    public void addFirst(String string) {
        SNode newNode = new SNode(string, this.head);
        this.head = newNode;
        if (this.isEmpty()) {
            this.tail = this.head;
        }
        this.size++;
    }

    public void addLast(String string) {
        SNode newNode = new SNode(string, null);
        if (this.isEmpty()) {
            this.head = newNode;
        } else {
            this.tail.setNext(newNode);
        }
        this.tail = newNode;
        this.size++;
    }

    public String removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        SNode temp = this.head;
        this.head = this.head.getNext();
        this.size--;
        if (this.isEmpty()) {
            this.tail = null;
        }
        temp.setNext(null);
        return temp.getElement();
    }

    public SNode delete(String string) {
        SNode currNode = this.head;
        SNode prevNode = null;
        while (currNode != null) {
            if (currNode.getElement().equals(string)) {
                if (prevNode == null) {
                    this.head = currNode.getNext();
                } else {
                    prevNode.setNext(currNode.getNext());
                }
                if (currNode == this.tail) {
                    this.tail = prevNode;
                }
                this.size--;
                return currNode;
            }
            prevNode = currNode;
            currNode = currNode.getNext();
        }
        return null;
    }

    public void display() {
        SNode current = this.head;
        while (current != null) {
            System.out.print(current + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public String toString() {
        SNode current = this.head;
        String list = "";
        while (current != null) {
            list += "\"" + current + "\",";
            current = current.getNext();
        }
        if (list.length() != 0) {
            return list.substring(0, list.length()-1);
        }
        return list;
    }
}