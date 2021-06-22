package keyword;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class StaticTest {
    private static final String VAR = "static_var";
    private static final Map<Integer,MobileGameDTO[]> taskIdMap = new ConcurrentHashMap<>();
    private static final int[] gameIds = new int[]{201110,201059};
    private String a = "";
    static {
        taskIdMap.put(201110,new MobileGameDTO[]{
                new MobileGameDTO("1004","试玩封神之刃拿金币","限量2000份！抢！还有游戏专属福利",30000,"url"),
                new MobileGameDTO("1005","充满1分钱即得福利","限量2000份！抢！还有游戏专属福利",50000,"url"),
                new MobileGameDTO("1006","充点小钱儿","限量2000份！抢！还有游戏专属福利",100000,"url")
                });
        taskIdMap.put(201059,new MobileGameDTO[]{
                new MobileGameDTO("1007","试玩封神之刃拿金币","限量2000份！抢！还有游戏专属福利",30000,"url"),
                new MobileGameDTO("1008","充满1分钱即得福利","限量2000份！抢！还有游戏专属福利",50000,"url"),
                new MobileGameDTO("1009","充点小钱儿","限量2000份！抢！还有游戏专属福利",100000,"url")
        });
//        a = "";
    }
    {
        staticMethod();
    }
    public static class StaticClass{

    }
    static void staticMethod(){
//        method();
//        System.out.println(a);
//        StaticTest staticTest = new StaticTest();
    }
    void method(){
        staticMethod();
        System.out.println(VAR);
        StaticClass staticClass = new StaticClass();
        System.out.println(this.VAR);
    }
    public Pair<List<MobileGameDTO>,List<MobileGameDTO>> getMobileGameList(){
        List<MobileGameDTO> mobileGameDTOS = new ArrayList<>();
        List<MobileGameDTO> mobileGameDTOS0 = new ArrayList<>();
        Random random = new Random();
        for (Integer gameId : gameIds){
            MobileGameDTO[] tasks = taskIdMap.get(gameId);
            for (int i=0;i<tasks.length;i++){
                boolean b3 = random.nextBoolean();
                System.out.println(Thread.currentThread().getName()+"---"+b3+"--"+tasks[i].getTaskId());
                MobileGameDTO gameDTO = tasks[i];
                if (i==0){
                    gameDTO.setBtn("去领奖");
                }else if (i==1){
                    gameDTO.setBtn("差一点");
                }else if (i==2){
                    gameDTO.setBtn("未完成");
                }
                if (b3){
                    gameDTO.setBtn("领金币");
                }
                gameDTO.setFinish(b3);
                gameDTO.setGameId(String.valueOf(gameId));
                MobileGameDTO gameDTO1 = new MobileGameDTO(gameDTO.getTaskId(), gameDTO.getTitle(), gameDTO.getDesc(), gameDTO.getCoinNum(), gameDTO.getUrl());
                gameDTO1.setFinish(b3);
                gameDTO1.setBtn(gameDTO.getBtn());
                gameDTO1.setGameId(gameDTO.getGameId());
                mobileGameDTOS.add(gameDTO);
                mobileGameDTOS0.add(gameDTO1);
                break;
            }
        }
        return new Pair<>(mobileGameDTOS,mobileGameDTOS0);
    }

    public static void main(String[] args) {
        StaticTest staticTest = new StaticTest();
        for (int i=0;i<10;i++){
            new Thread(() ->{
                Pair<List<MobileGameDTO>,List<MobileGameDTO>> pair = staticTest.getMobileGameList();
                System.out.println(Thread.currentThread().getName()+"---"+pair.getKey());
                System.out.println(Thread.currentThread().getName()+"===="+pair.getValue());
            }).start();
        }
    }
}