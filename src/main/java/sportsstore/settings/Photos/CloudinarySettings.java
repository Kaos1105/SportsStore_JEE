package sportsstore.settings.Photos;

public class CloudinarySettings {
    private String cloudName;
    private String apiKey;
    private String apiSecret;

    public String getCloudName() {
        return cloudName;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setCloudName(String cloudName) {
        this.cloudName = cloudName;
    }
}