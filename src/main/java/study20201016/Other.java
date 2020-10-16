package study20201016;

public class Other implements GMCommand{
    @Override
    public void process(Player player, String[] commands) {
        System.out.println("other...");
    }

    @Override
    public String getCommandType() {
        return null;
    }
}
