import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Subway {
    //static ArrayList<ArrayList<Station>> subwayGraph;
    static Map<Station, ArrayList<Station>> subwayGraph;

    public static void main(String[] args) {
        makeGraph(args[0]);
        // ../text/subway.txt

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;

                findShortestPath(input);
            } catch (IOException e) {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void makeGraph(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));

            String str;
            while ((str = reader.readLine()) != null) {
                // make graph

                // 역 정보 input
                // list에 station 저장, 정렬
                // TODO: 환승역은 어떻게 체크?

                if(str.equals("")){
                    System.out.println("find 공백 ");
                }

                // 경로 정보 input
                // 출발역은 key에 저장, 도착역은 List에 추가 <- list가 비어있을 때 생성 후 추가

                //System.out.println(str);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    private static void findShortestPath(String input) {
        // find shortest path 다익스트라
    }
}
