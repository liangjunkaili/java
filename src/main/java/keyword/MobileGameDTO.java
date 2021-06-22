package keyword;

public class MobileGameDTO {
    private String gameId;
    private String taskId;
    private boolean finish;
    private String title;
    private String desc;
    private int coinNum;
    private String url;
    private String btn;

    @Override
    public String toString() {
        return "MobileGameDTO{" +
                "gameId='" + gameId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", finish=" + finish +
//                ", title='" + title + '\'' +
//                ", desc='" + desc + '\'' +
//                ", coinNum=" + coinNum +
//                ", url='" + url + '\'' +
//                ", btn='" + btn + '\'' +
                '}';
    }

    public MobileGameDTO(String taskId, String title, String desc, int coinNum, String url) {
        this.taskId = taskId;
        this.title = title;
        this.desc = desc;
        this.coinNum = coinNum;
        this.url = url;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(int coinNum) {
        this.coinNum = coinNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }
}
