package com.googlecode.openwnn.legacy.CLOUDSONG;

import com.googlecode.openwnn.legacy.OpenWnnZHCN;
import com.googlecode.openwnn.legacy.WnnEngine;
import com.googlecode.openwnn.legacy.WnnWord;

import java.util.ArrayList;

/**
 * Created by lvhonghe on 16/4/28.
 */
public class CloudKeyboardInputManager implements CandidateCallback {


    private OpenWnnZHCN mOpenWnnZHCN = new OpenWnnZHCN();


    private StringBuilder currentPinYin = new StringBuilder();

    private StringBuilder commitedText = new StringBuilder();

    private int currentIndex = 0;

    private OnPinyinQueryed mOnPinyinQueryed;

    private ArrayList<WnnWord> mCurrentResult;

    public CloudKeyboardInputManager() {
        mOpenWnnZHCN.setCandidateCallBack(this);
    }

    public void processInput(char[] chars) {

        mOpenWnnZHCN.processSoftKeyboardCode(chars);
        currentPinYin.append(chars);
    }

    private void processSpace() {
        //// TODO: 16/4/29 空格做特殊处理
        currentPinYin.append(" ");
        keyboardActionCallBack();
    }

    public void processDel() {
        mOpenWnnZHCN.deleteBy1();
        if(currentPinYin.length() > 0 ) {
            currentPinYin.deleteCharAt(currentPinYin.length() - 1);
        }
        keyboardActionCallBack();
    }

    public void delAll() {
        int loop = currentPinYin.length();
        for(int i = 0; i < loop; ++i) {
            mOpenWnnZHCN.deleteBy1();
        }
        if(loop > 0) {
            currentPinYin.delete(0, loop);
        }
    }

    private static final int COUNT_LIMIT = 300;

    private void candidateQueryed(WnnEngine converter) {
        if (converter == null) {
            return;
        }


        /* Concatenate the candidates already got and the last one in dispFirst mode */
        int displayLimit = -1;
        /* Get candidates */
        WnnWord result = null;


        ArrayList<WnnWord> resultList = new ArrayList<WnnWord>();
        //// TODO: 16/4/18 增加退出条件
        int count = 0;
        while (displayLimit == -1 && count < COUNT_LIMIT ) {

            count++;

            try {
                result = converter.getNextCandidate();

                if (result == null) {
                    break;
                }
                resultList.add(result);

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }



            //TODO 增加刷新界面的机制
            //TODO 增加中途退出机制；

        }

        //TODO 回调机制
        mCurrentResult = resultList;
        keyboardActionCallBack();
        return;
    }


    private void appendCandidate(String candidate) {
        currentIndex += candidate.length();
    }

    public void setOnPinyinQueryed(OnPinyinQueryed onPYQueryed) {
        this.mOnPinyinQueryed = onPYQueryed;
    }

    private void keyboardActionCallBack() {
        if(mOnPinyinQueryed != null) {
            mOnPinyinQueryed.onPinyinQueryed(new PinyinQueryResult(mCurrentResult, currentPinYin.toString()));
        }
    }

    @Override
    public void displayCandidate(WnnEngine converter) {
        candidateQueryed(converter);
    }

    public void candidateSelected(WnnWord word) {
        mOpenWnnZHCN.commitTextSelected(word);
        currentPinYin.delete(0, currentPinYin.length());
    }
}
