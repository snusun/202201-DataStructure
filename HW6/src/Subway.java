import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Subway {
    //static ArrayList<ArrayList<Station>> subwayGraph;
    static Map<String, ArrayList<String>> stationMap = new HashMap<>();
    static Map<String, ArrayList<Cost>> subwayGraph = new HashMap<>();

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));

            boolean isFirst = true;
            String str;
            while ((str = reader.readLine()) != null) { // make graph
                if (str.equals("")) {
                    isFirst = false;
                    continue;
                    //System.out.println("find 공백 ");
                }

                if (isFirst) { // 역 정보 input
                    // list에 station 저장, 정렬 -> 일단 안필요한듯

                    // 환승역은 map(역이름, 역번호)에 저장
                    String[] stationInfo = str.split(" ");
                    String num = stationInfo[0];
                    String name = stationInfo[1];
                    if (stationMap.get(name) == null) {
                        ArrayList<String> stationList = new ArrayList<>();
                        stationList.add(num);
                        stationMap.put(name, stationList);
                    } else {
                        stationMap.get(name).add(num);
                    }
                } else { // 경로 정보 input
                    // 출발역은 key에 저장, 도착역은 List에 추가 <- list가 비어있을 때 생성 후 추가 (역번호)
                    String[] routeInfo = str.split(" ");
                    String start = routeInfo[0];
                    String goal = routeInfo[1];
                    int cost = Integer.parseInt(routeInfo[2]);

                    if (subwayGraph.get(start) == null) {
                        ArrayList<Cost> costList = new ArrayList<>();
                        costList.add(new Cost(goal, cost));
                        subwayGraph.put(start, costList);
                    } else {
                        subwayGraph.get(start).add(new Cost(goal, cost));
                    }
                }

                //System.out.println(str);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("File not found");
        }

        // TODO: 환승역끼리도 경로 생성, cost 0
        for (String key : stationMap.keySet()) {
            ArrayList<String> stationList = stationMap.get(key);
            int size = stationList.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(i==j) continue;
                    String start = stationList.get(i);
                    String goal = stationList.get(j);
                    if (subwayGraph.get(start) == null) {
                        ArrayList<Cost> costList = new ArrayList<>();
                        costList.add(new Cost(goal));
                        subwayGraph.put(start, costList);
                    } else {
                        subwayGraph.get(start).add(new Cost(goal));
                    }
                }
            }
        }

        for (String key : subwayGraph.keySet()) {
            for (Cost num : subwayGraph.get(key)) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    private static void findShortestPath(String input) {
        // find shortest path 다익스트라
        // 노션 참고
    }
}
