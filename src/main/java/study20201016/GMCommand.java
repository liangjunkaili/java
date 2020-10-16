package study20201016;

public interface GMCommand {
    void process(Player player,String[] commands);
    String getCommandType();
}
