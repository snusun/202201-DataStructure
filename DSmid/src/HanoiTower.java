public class HanoiTower {
    static int count =0;

    public void move(int number, String first, String center, String last){

        if(number == 1){
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center);
        } else{
            move(number -1, first, last, center);
            move(1, first, center, last);
            move(number -1, last, center, first);
        }
    }

    public void move4(int number, String first, String center1, String center2, String last){

        if(number == 1){
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center1);
        } else if(number == 2) {
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center2);
            ++ count;
            System.out.println(count + ": "+ first + " -> " + center1);
            ++ count;
            System.out.println(count + ": "+ center2 + " -> " + center1);
        } else {
            move4(number - 2, first, last, center1, center2);
            move4(2, first, center1, center2, last);
            move4(number -2, last, center1, center2, first);
        }
    }
}