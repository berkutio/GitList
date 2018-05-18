package com.gitlist.network;


import android.content.res.Resources;

import com.gitlist.R;

public class BaseProvider {

    private Resources resources;

    public BaseProvider(Resources resources) {
        this.resources = resources;
    }

    public String getBaseUrl(){
        return resources.getString(R.string.base_url);
    }

}
