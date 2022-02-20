package com.example.helloworld;

import com.google.gson.annotations.SerializedName;

public class DataModal {

    @SerializedName("result")
    private String result;

    @SerializedName("destination")
    private String destination;

    @SerializedName("preventScrape")
    private Boolean preventScrape;

    @SerializedName("owoify")
    private Boolean owoify;

    @SerializedName("link")
    private String link;

    public DataModal (String link){
        this.link = link;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Boolean getPreventScrape() {
        return preventScrape;
    }

    public void setPreventScrape(Boolean preventScrape) {
        this.preventScrape = preventScrape;
    }

    public Boolean getOwoify() {
        return owoify;
    }

    public void setOwoify(Boolean owoify) {
        this.owoify = owoify;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
