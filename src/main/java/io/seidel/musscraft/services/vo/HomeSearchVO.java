package io.seidel.musscraft.services.vo;

import io.seidel.musscraft.database.models.MussPlayer;

public class HomeSearchVO {
    
    private String name;

    private MussPlayer owner;

    private Boolean isPublic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MussPlayer getOwner() {
        return owner;
    }

    public void setOwner(MussPlayer owner) {
        this.owner = owner;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
