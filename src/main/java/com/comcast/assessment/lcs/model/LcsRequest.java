package com.comcast.assessment.lcs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LcsRequest implements Serializable {

    /**
     *
     */


    private List<StringWithValue> setOfStrings = new ArrayList<>();

    public List<StringWithValue> getSetOfStrings() {
        return setOfStrings;
    }

    public void setSetOfStrings(List<StringWithValue> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }
}
