package study20201016;

public class MenKe implements GMCommand{
    @Override
    public void process(Player player, String[] commands) {
        //todo
        System.out.println("MenKe.......");
    }

    @Override
    public String getCommandType() {
        return CommandType.MENKE.getString();
    }
}
