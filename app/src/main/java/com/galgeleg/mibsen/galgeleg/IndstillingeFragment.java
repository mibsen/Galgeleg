package com.galgeleg.mibsen.galgeleg;


import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class IndstillingeFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.preferences);
    }
}