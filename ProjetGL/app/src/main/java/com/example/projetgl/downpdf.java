package com.example.projetgl;

public class downpdf {
    String Name,Link;

    public downpdf(String Name, String Link){
        this.Link=Link;
        this.Name=Name;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
}
