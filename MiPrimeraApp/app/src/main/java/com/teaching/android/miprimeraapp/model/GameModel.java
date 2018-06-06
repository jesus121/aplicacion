package com.teaching.android.miprimeraapp.model;

public class GameModel {
    private  int id;
    private String name;
    private String description;
    private String officialWebsiteURL;
    private  String icon;
    private String background;

    public GameModel (){

    }

    public GameModel(int id, String name, String description, String officialWebsiteURL, String icon, String background) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.officialWebsiteURL = officialWebsiteURL;
        this.icon = icon;
        this.background = background;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOfficialWebsiteURL() {
        return officialWebsiteURL;
    }

    public void setOfficialWebsiteURL(String officialWebsiteURL) {
        this.officialWebsiteURL = officialWebsiteURL;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

}
