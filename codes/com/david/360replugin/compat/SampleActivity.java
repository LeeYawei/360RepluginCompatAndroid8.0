
package com.david.360replugin.compat;

import android.app.Activity;
import android.os.Bundle;

/**
 * description: java class function
 * author: liyawei
 * date: 2019/4/12 11:26 AM
 */
public class SampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RepluginCompatUtils.onCreate(this);
        super.onCreate(savedInstanceState);
        // ...
    }

}
