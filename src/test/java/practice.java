import java.util.Arrays;
class parent{

}
public class practice extends parent{
    public static void reverseArray(String [] arr, int start, int end){
        String temp;
        if(start>=end){
            return;
        }
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseArray(arr, start+1, end-1);
    }
    static String leftrotate(String str, int d)
    {
        String ans = str.substring(d) + str.substring(0, d);
        return ans;
    }
    public static void main(String[] args) {
//        String [] arr = {"a"," ","b"," ","c"," ","d"};
//        reverseArray(arr, 0, arr.length-1);
//        Arrays.stream(arr).forEach(str -> System.out.print(str+"_"));
          String a = "ANKUR";
          System.out.println(a.substring(0,1));
    }
}
