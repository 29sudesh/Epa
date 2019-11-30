package com.eap.lifepilot;

import com.eap.lifepilot.fragments.Menufragment;

/**
 * Created by Sudesh Bishnoi on 29-Sep-19.
 */

public interface MenuListener {
    void onMenuItemClick(int selectedId);

    void getInstanceOfMenuFragment(Menufragment menufragment);
}
