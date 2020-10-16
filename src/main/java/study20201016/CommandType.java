package study20201016;

public enum CommandType {
    MENKE("menke"),
    OTHER("other");

    private String string;
    CommandType(String string) {
        this.string = string;
    }

    public String getString(){
        return string;
    }
    public static void main(String[] args) {
        System.out.println(MENKE.getString());
    }
}
