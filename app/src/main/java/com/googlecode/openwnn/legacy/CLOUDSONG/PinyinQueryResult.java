package com.googlecode.openwnn.legacy.CLOUDSONG;

import com.googlecode.openwnn.legacy.WnnWord;

import java.util.ArrayList;

/**
 * Created by lvhonghe on 16/4/28.
 */
public class PinyinQueryResult {
    private ArrayList<WnnWord> candidateList;
    private String currentInput;

    public PinyinQueryResult(ArrayList<WnnWord> candidateList, String currentInput) {
        this.candidateList = candidateList;
        this.currentInput = currentInput;
    }

    public ArrayList<WnnWord> getCandidateList() {
        return  candidateList;
    }

    public String getCurrentInput() {
        return currentInput;
    }
}
