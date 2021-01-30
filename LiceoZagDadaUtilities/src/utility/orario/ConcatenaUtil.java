package utility.orario;

public class ConcatenaUtil {

    public static void main(String[] args) {
        String s="1E\n" +
                "1D\n" +
                "1F\n" +
                "2H\n" +
                "3H\n" +
                "4H";
        StringBuilder sb=new StringBuilder();
        String[] split = s.split("\n");
        for (String s1 : split) {
            if(sb.length()>0){
                sb.append("/");
            }
            sb.append(s1);
        }

        System.out.println(sb);
    }
}
