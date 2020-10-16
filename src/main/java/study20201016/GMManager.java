package study20201016;

import java.util.HashMap;
import java.util.Map;

public class GMManager {
    public Map<String,GMCommand> getAll(){

        Map<String,GMCommand> map = new HashMap<>();
        map.put("menke",new MenKe());
        map.put("other",new Other());
        return map;
    }
    public void doCommand(String command){
        GMCommand gmCommand = getAll().get(command);
        //自己构造Player、commands
        gmCommand.process(new Player(),new String[]{});
    }

    public static void main(String[] args) {
        GMManager gmManager = new GMManager();
        gmManager.doCommand("menke");
        gmManager.doCommand("other");
    }
}
