package com.fragmentcrash.fragmentmanagerimplcrash;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;


public class MainActivity extends FragmentActivity {

    public static final int SCREEN_SIZE_TABLET_SMALL = 600;

    private final static String FRAGMENT_A = "fragment_a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSmallestWidth(this) < SCREEN_SIZE_TABLET_SMALL) {
            createAndAttachFragment();
        }
    }

    private void createAndAttachFragment() {
        Fragment fragment = new Fragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment_a, fragment, FRAGMENT_A)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Get the smallest of the device's two screen dimensions.
     * @param activity The currently displayed Activity.
     * @return The smallest width, in DP
     */
    public static int getSmallestWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        float scaleFactor = metrics.density;

        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        float smallestWidth = Math.min(widthDp, heightDp);

        return (int) smallestWidth;
    }
}
