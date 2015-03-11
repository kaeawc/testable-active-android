package io.kaeawc.activeandroidapp.robolectric;

import android.app.Activity;

import org.apache.commons.lang3.StringUtils;
import org.junit.runners.model.InitializationError;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.internal.SdkEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;

import java.io.File;
import java.util.Properties;

import io.kaeawc.activeandroidapp.BuildConfig;

public class TestRunner extends RobolectricTestRunner {

    private static final int MAX_SDK_SUPPORTED_BY_ROBOLECTRIC = 18;
    private static final int MIN_SDK_SUPPORTED_BY_MULTIDEX = 4;

    // Define any Robolectric Shadows
    private static final String[] SHADOWS = new String[]{};

    // Paths to create Robolectric's App Manifest
    private static final String MANIFEST_PATH = "src/test/AndroidManifest.xml";
    private static final String RESOURCES_PATH = "build/intermediates/res/%s/debug";
    private static final String ASSETS_PATH = "src/main/assets";

    // Required for appcompat-v7 Activities
    private static final String ACTIVITY_THEME = "@style/RobolectricAppTheme";

    public TestRunner(Class<?> testClass)
            throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {

        String resourcesPath = String.format(RESOURCES_PATH, BuildConfig.FLAVOR.toLowerCase());
        String manifestPath = MANIFEST_PATH;
        String assetsPath = ASSETS_PATH;

        // android studio has a different execution root for tests than pure gradle
        // so we avoid here manual effort to get them running inside android studio
        if (!new File(manifestPath).exists()) {
            manifestPath = "app/" + manifestPath;
            resourcesPath = "app/" + resourcesPath;
            assetsPath = "app/" + assetsPath;
        }

        return new AndroidManifest(Fs.fileFromPath(manifestPath), Fs.fileFromPath(resourcesPath), Fs.fileFromPath(assetsPath)) {

            @Override
            public int getTargetSdkVersion() {
                return MAX_SDK_SUPPORTED_BY_ROBOLECTRIC;
            }

            @Override
            public int getMinSdkVersion() { return MIN_SDK_SUPPORTED_BY_MULTIDEX; }

            @Override
            public String getThemeRef(Class<? extends Activity> activityClass) {
                return ACTIVITY_THEME;
            }

        };
    }

    @Override
    protected void configureShadows(SdkEnvironment sdkEnvironment, Config config) {
        Properties properties = new Properties();
        properties.setProperty("shadows", StringUtils.join(SHADOWS, " "));
        super.configureShadows(sdkEnvironment, new Config.Implementation(config, Config.Implementation.fromProperties(properties)));
    }
}