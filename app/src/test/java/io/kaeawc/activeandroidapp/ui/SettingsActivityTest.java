package io.kaeawc.activeandroidapp.ui;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

import io.kaeawc.activeandroidapp.robolectric.TestRunner;

@RunWith(TestRunner.class)
public class SettingsActivityTest extends ActivitySpec<SettingsActivity> {

    @Test
    public void testLayoutInflation() {

        SettingsActivity activity = started(SettingsActivity.class);

        assertTrue(activity.hasHeaders());

    }
}
