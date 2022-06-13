public class Cost implements Comparable<Cost>{
    String number;
    long cost;

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
        if(this.cost > o.cost) {
            return 1;
        } else if(this.cost < o.cost){
            return -1;
        } else return 0;
    }
}
