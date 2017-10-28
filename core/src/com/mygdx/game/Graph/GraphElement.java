package com.mygdx.game.Graph;

/**
 * Created by Hegedüs Csongor on 10/28/2017.
 */

public class GraphElement {

    private String situationId;

    public GraphElement(String s) {
        situationId = s.split(" ")[0];
    }

    public String getSituationId() {
        return situationId;
    }

    public void setSituationId(String situationId) {
        this.situationId = situationId;
    }

    @Override
    public String toString() {
        return situationId;
    }
}
