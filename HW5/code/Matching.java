import java.io.*;
import java.util.LinkedList;
import java.util.Hashtable;

public class Matching {
    static String[] fileData;
    static Hashtable<Integer, AVLTree<String, Position>> hashtable;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;

                command(input);
            } catch (IOException e) {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void command(String input) {
        // TODO : 아래 문장을 삭제하고 구현해라.
        //System.out.println("<< command 함수에서 " + input + " 명령을 처리할 예정입니다 >>");
        String[] inputInfo = input.split(" ");
        if (inputInfo[0].equals("<")) {
            fileData = readFile(inputInfo[1]);
            hashtable = makeHashTable(fileData);
        } else if (inputInfo[0].equals("@")) {
            int indexNumber = Integer.parseInt(inputInfo[1]);
            findSlot(indexNumber);
        } else if (inputInfo[0].equals("?")) {
            String pattern = input.substring(2);
            System.out.println(searchPattern(pattern));
        }
    }

    private static String[] readFile(String filePath) {

        LinkedList<String> strList = new LinkedList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String str;
            while ((str = reader.readLine()) != null) {
                strList.add(str);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] result = new String[strList.size()];
        for (int i = 0; i < strList.size(); i++) {
            result[i] = strList.get(i);
        }

        return result;
    }

    private static Hashtable<Integer, AVLTree<String, Position>> makeHashTable(String[] data) {
        Hashtable<Integer, AVLTree<String, Position>> hashtable = new Hashtable<>();

        for (int i = 0; i < data.length; i++) {
            String str = data[i];
            for (int j = 0; j < str.length() - 5; j++) {
                String subString = str.substring(j, j + 6);
                int slotNum = calculateSlotNum(subString);

                if (hashtable.get(slotNum) == null) {
                    hashtable.put(slotNum, new AVLTree<>());
                }

                    System.out.println("subString");
					System.out.println(subString);
                    System.out.println("-----");


                hashtable.get(slotNum).insert(subString, new Position(i + 1, j + 1));
                //hashtable.get(slotNum).BFS();
            }
        }

        return hashtable;
    }

    private static void findSlot(int index) {
        if (hashtable.get(index) == null) System.out.println("EMPTY");
        else {
            AVLTree<String, Position> avlTree = hashtable.get(index);
            avlTree.preOrderTraversal(avlTree.getRoot());
			//System.out.println(avlTree.preOrderAsString());
        }
    }

    private static String searchPattern(String pattern) {
        String searchResult = "";

        if (pattern.length() == 6) {
            int slotNum = calculateSlotNum(pattern);

            LinkedList<Position> positions = getPositions(slotNum, pattern);
            if (positions == null) {
                return "(0, 0)";
            } else {
                for (Position position : positions) {
                    searchResult += position + " ";
                }
            }
        } else { // 길이 6 초과
            int slotNum = calculateSlotNum(pattern.substring(0, 6));

            LinkedList<Position> positions = getPositions(slotNum, pattern.substring(0, 6));
            if (positions == null) {
                return "(0, 0)";
            }
            LinkedList<Position> concatPosition = new LinkedList<>();

            for (Position start : positions) {
                for (int i = 0; i < pattern.length(); i += 6) {
                    if (pattern.length() - i <= 6) {
                        int index = pattern.length() - 6;
                        String lastStr = pattern.substring(index, pattern.length());
                        int newSlotNum = calculateSlotNum(lastStr);
                        LinkedList<Position> newPositions = getPositions(newSlotNum, lastStr);
						if (newPositions==null) {
							continue;
						}
						for (Position nextPosition : newPositions) {
							if (start.i == nextPosition.i && start.j + index == nextPosition.j) {
								if(!concatPosition.contains(start)) {
									concatPosition.add(start);
								}
							}
						}

                    } else {
                        String subStr = pattern.substring(i, i + 6);
                        int newSlotNum = calculateSlotNum(subStr);
						LinkedList<Position> newPositions = getPositions(newSlotNum, subStr);
                        if (newPositions==null) {
                            continue;
                        }

                        boolean isExist = false;
                        for (Position nextPosition : newPositions) {
                            if (start.i==nextPosition.i && start.j + i == nextPosition.j) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            break;
                        }
                    }
                }
            }

            for(Position position: concatPosition){
            	searchResult += position + " ";
			}
        }

        return searchResult.trim();
    }

    private static int calculateSlotNum(String str) {
        char[] chars = str.toCharArray();
        int ascii = 0;
        for (char c : chars) {
            ascii += c;
        }
        return ascii % 100;
    }

    private static LinkedList<Position> getPositions(int slotNum, String pattern) {
        if (hashtable.get(slotNum) == null) {
            return null;
        }
        AVLTree<String, Position> avlTree = hashtable.get(slotNum);
        AVLNode<String, Position> avlNode = avlTree.search(pattern);
        return avlNode.item;
    }
}
