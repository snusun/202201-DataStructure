public class Cost implements Comparable<Cost>{
    String number;
    long cost=0;

    Cost(String number){
        this.number = number;
    }

    Cost(String number, long cost){
        this.number = number;
        this.cost = cost;
    }

    @Override
    public String toString(){
        return number + " " + cost;
    }

    @Override
    public int compareTo(Cost o) {
        return Long.compare(this.cost, o.cost);
    }
}
