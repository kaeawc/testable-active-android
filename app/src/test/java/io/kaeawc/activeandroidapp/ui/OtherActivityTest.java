package io.kaeawc.activeandroidapp.ui;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.kaeawc.activeandroidapp.robolectric.TestRunner;

@RunWith(TestRunner.class)
public class OtherActivityTest extends ActivitySpec<OtherActivity> {

    @Test
    public void testLayoutInflation() {

        OtherActivity activity = created(OtherActivity.class);

    }
}
