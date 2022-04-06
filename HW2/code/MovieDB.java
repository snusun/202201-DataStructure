import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * <p>
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를
 * 유지하는 데이터베이스이다.
 */
public class MovieDB {
    public MovieDB() {
        // FIXME implement this
        // HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한
        // MyLinkedList 타입의 멤버 변수를 초기화 한다.
        MyLinkedList<MyLinkedList<String>> movieDB = new MyLinkedList<>();
    }

    public void insert(MovieDBItem item) {
        // FIXME implement this
        // Insert the given item to the MovieDB.

        // Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public void delete(MovieDBItem item) {
        // FIXME implement this
        // Remove the given item from the MovieDB.

        // use iterator

        // Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public MyLinkedList<MovieDBItem> search(String term) {
        // FIXME implement this
        // Search the given term from the MovieDB.
        // You should return a linked list of MovieDBItem.
        // The search command is handled at SearchCmd class.

        // Printing search results is the responsibility of SearchCmd class.
        // So you must not use System.out in this method to achieve specs of the assignment.

        // This tracing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);

        // FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
        // This code is supplied for avoiding compilation error.
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

        return results;
    }

    public MyLinkedList<MovieDBItem> items() {
        // FIXME implement this
        // Search the given term from the MovieDatabase.
        // You should return a linked list of QueryResult.
        // The print command is handled at PrintCmd class.

        // Printing movie items is the responsibility of PrintCmd class.
        // So you must not use System.out in this method to achieve specs of the assignment.

        // Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: ITEMS\n");

        // FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
        // This code is supplied for avoiding compilation error.
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

        return results;
    }
}

class Genre extends Node<String> implements Comparable<Genre> {
    public Genre(String name) {
        super(name);
        //throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public int compareTo(Genre o) {
        return this.getItem().compareTo(o.getItem());
        //throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
        //throw new UnsupportedOperationException("not implemented yet");
    }
}

class MovieList extends MyLinkedList<String> implements ListInterface<String> {
    public MovieList() {
        head = new Genre("temp"); // 일단 이렇게 해두기 원래 null (test용)
    }

//    @Override
//    public Iterator<String> iterator() {
//        throw new UnsupportedOperationException("not implemented yet");
//    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
        //throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public int size() {
        return super.size();
        //throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public void add(String item) {
        if (isEmpty()) {
            super.add(item);
        } else if (size() == 1) {
            if (first().compareTo(item) == 0) {
                return;
            }
            if (first().compareTo(item) > 0) {
                head.insertNext(item);
            } else {
                head.getNext().insertNext(item);
            }
            numItems += 1;
        } else {
            Node<String> prev = head;
            Node<String> curr = head.getNext();
            while (curr.getNext()!=null){
                if(curr.getItem().compareTo(item) == 0){
                    curr = null;
                    break;
                } else if(curr.getItem().compareTo(item) > 0) {
                    curr = prev;
                    break;
                } else if(curr.getItem().compareTo(item) < 0 && curr.getNext().getItem().compareTo(item) > 0){
                    break;
                }
                prev = curr;
                curr = curr.getNext();
            }
            if(curr==null){
                return;
            } else {
                curr.insertNext(item);
            }
            numItems += 1;
        }
        //throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public String first() {
        return super.first();
        //throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public void removeAll() {
        super.removeAll();
        //throw new UnsupportedOperationException("not implemented yet");
    }
}