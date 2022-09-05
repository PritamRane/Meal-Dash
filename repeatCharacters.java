public class repeatCharacters {
    public void repeat(String str, int times){
        for (int i=0; i<times; i++){
            System.out.print(str);
        }
    }

    private static String fill(char ch, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private static int getMaxLength(String... strings) {
        int len = Integer.MIN_VALUE;
        for (String str : strings) {
            len = Math.max(str.length(), len);
        }
        return len;
    }

    private static String padString(String str, int len) {
        StringBuilder sb = new StringBuilder(str);
        return sb.append(fill(' ', len - str.length())).toString();
    }
    public static String printBox(String... strings){
        String lines = "";
        int maxBoxWidth = getMaxLength(strings);
    String line = "+" + fill('-', maxBoxWidth + 2) + "+";
    System.out.println(line);
    lines+=line;
    for (String str : strings) {
        System.out.printf("| %s |%n", padString(str, maxBoxWidth));
        lines+=String.format("\n| %s |%n",padString(str, maxBoxWidth));
    }
    System.out.println(line);
    lines+=line;

    return lines;
    }


    public static void slowChars(String data,int speed){
        String text = data;
    for(int i=0; i<text.length();i++){

    System.out.print(text.charAt(i));
    try {
        Thread.sleep(speed); 
        } catch (Exception e) {
        e.printStackTrace();
        }
    }
    }

    

    
}

