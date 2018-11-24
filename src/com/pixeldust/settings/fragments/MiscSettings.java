/*
 * Copyright (C) 2018 The Pixel Dust Project
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

package com.pixeldust.settings.fragments;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;

import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.search.Indexable;
import com.android.settings.SettingsPreferenceFragment;

import com.android.internal.util.pixeldust.PixeldustUtils;

import java.util.ArrayList;
import java.util.List;

public class MiscSettings extends SettingsPreferenceFragment implements Indexable {

    private static final String AMBIENT_PLAY_CAT_KEY = "ambient_play_cat";
    private PreferenceCategory mAmbientPlayCategory;
private static final String KEY_DEVICE_PART = "advanced_controls";
    private static final String KEY_DEVICE_PART_PACKAGE_NAME = "com.thht.settings.device";
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.pixeldust_settings_misc);

        final PreferenceScreen prefScreen = getPreferenceScreen();
        Context mContext = getActivity();

        if (!PixeldustUtils.isAmbientPlayAvailable(mContext)) {
            mAmbientPlayCategory = (PreferenceCategory) findPreference(AMBIENT_PLAY_CAT_KEY);
            prefScreen.removePreference(mAmbientPlayCategory);
        }
                // Advanced Controls
        if (!com.pixeldust.settings.preferences.Utils.isPackageInstalled(getActivity(), KEY_DEVICE_PART_PACKAGE_NAME)) {
            getPreferenceScreen().removePreference(findPreference(KEY_DEVICE_PART));
        }

    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.PIXELDUST;
    }

    public static final Indexable.SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(Context context,
                        boolean enabled) {
                    ArrayList<SearchIndexableResource> result =
                            new ArrayList<SearchIndexableResource>();
                     SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.pixeldust_settings_misc;
                    result.add(sir);
                    return result;
                }
                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    ArrayList<String> result = new ArrayList<String>();
                    return result;
                }
    };
}
