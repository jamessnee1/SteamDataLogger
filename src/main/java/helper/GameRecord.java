package helper;

public class GameRecord {

    private String appId;
    private String gameName;
    private String imgIconUrl;
    private String imgLogoUrl;

    public GameRecord(String appId, String gameName, String imgIconUrl, String imgLogoUrl){
        this.appId = appId;
        this.gameName = gameName;
        this.imgIconUrl = imgIconUrl;
        this.imgLogoUrl = imgLogoUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getImgIconUrl() {
        return imgIconUrl;
    }

    public String getImgLogoUrl() {
        return imgLogoUrl;
    }
}
