/*
 * Copyright (C) 2008,2009  OMRON SOFTWARE Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.googlecode.openwnn.legacy.ZH.CN;

import com.googlecode.openwnn.legacy.OpenWnnEvent;
import com.googlecode.openwnn.legacy.OpenWnnZHCN;
import com.googlecode.openwnn.legacy.UserDictionaryToolsEdit;
import com.googlecode.openwnn.legacy.UserDictionaryToolsList;

import android.view.View;

/**
 * The user dictionary's word editor class for Chinese IME.
 *
 * @author Copyright (C) 2009 OMRON SOFTWARE CO., LTD.  All Rights Reserved.
 */
public class UserDictionaryToolsEditZHCN extends UserDictionaryToolsEdit {
    /**
     * Constructor
     */
    public UserDictionaryToolsEditZHCN() {
        super();
        initialize();
    }

    /**
     * Constructor 
     *
     * @param focusView         The view
     * @param focusPairView     The pair view
     */
    public UserDictionaryToolsEditZHCN(View focusView, View focusPairView) {
        super(focusView, focusPairView);
        initialize();
    }

    /**
     * Initialize the parameters
     */
    public void initialize() {
        mListViewName = "com.googlecode.openwnn.legacy.ZH.CN.UserDictionaryToolsListZHCN";
        mPackageName  = "com.googlecode.openwnn.legacy";
    }

    /** @see com.googlecode.openwnn.legacy.UserDictionaryToolsEdit#sendEventToIME */
    @Override protected boolean sendEventToIME(OpenWnnEvent ev) {
        try {
            return OpenWnnZHCN.getInstance().onEvent(ev);
        } catch (Exception ex) {
            /* do nothing if an error occurs */
        }
        return false;
    }

    /** @see com.googlecode.openwnn.legacy.UserDictionaryToolsEdit#createUserDictionaryToolsList */
    @Override protected UserDictionaryToolsList createUserDictionaryToolsList() {
        return new UserDictionaryToolsListZHCN();
    }
}
