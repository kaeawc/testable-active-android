package io.kaeawc.activeandroidapp.ui;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import io.kaeawc.activeandroidapp.robolectric.TestRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(TestRunner.class)
public abstract class ActivitySpec<T extends Activity> {

    protected T created(Class<T> kclass) {
        T activity = Robolectric
                .buildActivity(kclass)
                .create()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T resumed(Class<T> kclass) {
        T activity = Robolectric
                .buildActivity(kclass)
                .resume()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T started(Class<T> kclass) {
        T activity = Robolectric
                .buildActivity(kclass)
                .create()
                .start()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T paused(Class<T> kclass) {
        T activity = Robolectric
                .buildActivity(kclass)
                .create()
                .start()
                .pause()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T stopped(Class<T> kclass) {
        T activity = Robolectric
                .buildActivity(kclass)
                .create()
                .start()
                .pause()
                .stop()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T destroyed(Class<T> kclass) {
        T activity = Robolectric
                .buildActivity(kclass)
                .create()
                .start()
                .pause()
                .stop()
                .destroy()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T configChange(Class<T> kclass, ActivityController activityController) {
        Bundle bundle = new Bundle();
        activityController
                .saveInstanceState(bundle)
                .pause()
                .stop()
                .destroy();

        return Robolectric
                .buildActivity(kclass)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle)
                .resume()
                .visible()
                .get();
    }

    protected T created(Class<T> kclass, Intent intent) {
        T activity = Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .create()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T resumed(Class<T> kclass, Intent intent) {
        T activity = Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .resume()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T started(Class<T> kclass, Intent intent) {
        T activity = Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .create()
                .start()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T paused(Class<T> kclass, Intent intent) {
        T activity = Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .create()
                .start()
                .pause()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T stopped(Class<T> kclass, Intent intent) {
        T activity = Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .create()
                .start()
                .pause()
                .stop()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T destroyed(Class<T> kclass, Intent intent) {
        T activity = Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .create()
                .start()
                .pause()
                .stop()
                .destroy()
                .get();

        assertNotNull(activity);
        return activity;
    }

    protected T configChange(Class<T> kclass, ActivityController activityController, Intent intent) {
        Bundle bundle = new Bundle();
        activityController.saveInstanceState(bundle).pause().stop().destroy();
        return Robolectric
                .buildActivity(kclass)
                .withIntent(intent)
                .create(bundle)
                .start()
                .restoreInstanceState(bundle)
                .resume()
                .visible()
                .get();
    }

    /**
     * Get expected message from string resources
     * @param activity Activity reference
     * @return Resources if null, otherwise fail test
     */
    protected Resources getResources(T activity) {
        //
        Resources resources = activity.getResources();

        if (resources == null) {
            fail("Could not get reference to Resources");
        }

        return resources;
    }

    protected String getResourceString(T activity, int resource) {

        Resources resources = getResources(activity);
        return resources.getString(resource);
    }
}
