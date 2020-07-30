import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
public class testGen {
    public int findMinimum(String str){
        Set<Character> set = new HashSet<>();
        for(int i=0;i<str.length();i++){
            set.add(str.charAt(i));
        }
        int finalCount = set.size();
        int size = Integer.MAX_VALUE;
        String res = "";
        for(int i=0;i<str.length();i++){
            int [] visited = new int[256];
            Arrays.fill(visited,0);
            int count = 0;
            String subStr = "";
            for(int j=i;j<str.length();j++){
                subStr += str.charAt(j);
                if(visited[str.charAt(j)]==0){
                    count++;
                    visited[str.charAt(j)]=1;
                }
                if(count==finalCount){
                    break;
                }
            }
            if(subStr.length() < size && count == finalCount){
                res = subStr;
            }
        }
        System.out.println(res);
        return res.length();
    }
    static int max = 256;
    public int findMin(String str){
        int dc = 0;
        boolean [] visited = new boolean[max]; Arrays.fill(visited, false);
        for(int i=0;i<str.length();i++){
            if(visited[str.charAt(i)]==false){
                visited[str.charAt(i)]=true;
                dc++;
            }
        }
        int n = str.length();
        int start = 0, start_index = -1;
        int [] cCount = new int[max];
        int count = 0;
        int min_len = Integer.MAX_VALUE;
        for(int j=0;j<n;j++){
            cCount[str.charAt(j)]++;
            if(cCount[str.charAt(j)]==1){
                count++;
            }
            if(count == dc){
                while(cCount[str.charAt(start)]>1){
                    if(cCount[str.charAt(start)]>1){
                        cCount[str.charAt(start)]--;
                        start++;
                    }
                }
                int len_window = j - start + 1;
                if(len_window<min_len){
                    min_len = len_window;
                    start_index = start;
                }
            }
        }
        String res = str.substring(start_index, start_index+min_len);
        System.out.println(res);
        return res.length();
    }
    static HashSet<String> set = new HashSet<>();
    public static void subSequences(String a, int n){
        for(int i=0;i<n;i++){

            for(int j=n;j>i;j--){
                String sub_str = a.substring(i,j);

                if(!set.contains(sub_str)){
                    set.add(sub_str);
                }

                for(int k=0;k<sub_str.length()-1;k++){

                    StringBuffer sb = new StringBuffer(sub_str);
                    sb.deleteCharAt(k);
                    if(!set.contains(sb)){
                        subSequences(sb.toString(), sb.length());
                    }

                }

            }
        }
    }
    public static void main(String[] args) {

    }
}
