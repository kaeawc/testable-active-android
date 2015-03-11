package io.kaeawc.activeandroidapp.ui;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.kaeawc.activeandroidapp.R;
import io.kaeawc.activeandroidapp.robolectric.TestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(TestRunner.class)
public class MainActivityTest extends ActivitySpec<MainActivity> {

    @Test
    public void testLayoutInflation() {

        MainActivity activity = created(MainActivity.class);

        String actual = getHelloWorldText(activity);
        String expected = getResourceString(activity, R.string.hello_world);

        assertEquals(expected, actual);
    }

    /**
     * Get actual text from MainActivity TextView
     * @param activity MainActivity reference
     * @return String current hello world text
     */
    private String getHelloWorldText(MainActivity activity) {

        //
        TextView helloWorldTextView = (TextView) activity.findViewById(R.id.hello_world_text);

        if (helloWorldTextView == null) {
            fail("Could not get reference to TextView");
        }

        return (String) helloWorldTextView.getText();
    }
}
