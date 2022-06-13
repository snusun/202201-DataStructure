public class Cost {
    String number;
    int cost;

    Cost(String number){
        this.number = number;
    }

    Cost(String number, int cost){
        this.number = number;
        this.cost = cost;
    }

    @Override
    public String toString(){
        return number + " " + cost;
    }
}
