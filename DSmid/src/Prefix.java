public class Prefix {

    public String toPostfix(String pre){
        char ch = pre.charAt(0);
        pre = pre.substring(1);
        //System.out.println(pre);
        System.out.println(ch);
        System.out.println(pre);
        System.out.println();
        if(Character.isDigit(ch) || Character.isAlphabetic(ch)){
            return Character.toString(ch);
        } else {
            String postfix1 = toPostfix(pre);
            String postfix2 = toPostfix(pre);
            return postfix1 + postfix2 + ch;
        }
    }
}
