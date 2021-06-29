package retry;

public class FinalTest {
    private String innerString;
    public FinalTest(String innerString){
        this.innerString = innerString;
    }
    final String str = "";
    final void test(){

    }
    public int length() {
        return innerString.length();
    }

    public static void main(String[] args) {
        new FinalTest("aaaaa").length();
    }
}
