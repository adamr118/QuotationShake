package dadm.arahmou.quotationshake.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import dadm.arahmou.quotationshake.R
import dadm.arahmou.quotationshake.databinding.FragmentSettingsBinding

class FragmentSettings: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }


}