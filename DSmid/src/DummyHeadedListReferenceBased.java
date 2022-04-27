public class DummyHeadedListReferenceBased extends ListReferenceBased {
    private Node head;
    private int numItems;

    public DummyHeadedListReferenceBased() {
        numItems=0;
        head = new Node(0, null);
    }


    private Node find(int index) {
        Node curr = head;
        if(index==0) return head;
        for (int i = 1; i <= index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }

    @Override
    public Object get(int index){
        //System.out.println("get: " + index);
        if(index>=1 && index <= numItems){
            Node curr = find(index);
            Object dataItem = curr.getItem();
            return dataItem;
        } else {
            System.out.println("invalid index");
            return null;
        }
    }

    @Override
    public void remove(int index) {
        if (index >= 1 && index <= numItems) {
            Node prev = find(index - 1);
            Node curr = prev.getNext();
            prev.setNext(curr.getNext());
            numItems--;
        } else {
            System.out.println("invalid index");
        }
    }

    @Override
    public void add(int index, Object item) {
        System.out.println(index);
        if (index >= 1 && index <= numItems + 1) {
            Node prev = find(index - 1);
            Node newNode = new Node(item, prev.getNext());
            prev.setNext(newNode);
            numItems++;
        } else {
            System.out.println("invalid index");
        }
    }
}
