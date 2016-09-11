package com.dziewit.marek.schultztablesgame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;
import org.robolectric.util.ReflectionHelpers;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Marek Dziewit
 * on 11.09.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    public static final String TOOLBAR_FIELD_NAME = "toolbar";
    public static final String DRAWER_LAYOUT_FIELD_NAME = "drawerLayout";

    private ActivityController<MainActivity> mainActivityActivityController;

    @Before
    public void setUp() throws Exception {
        mainActivityActivityController = Robolectric.buildActivity(MainActivity.class);
    }

    @After
    public void tearDown() throws Exception {
        mainActivityActivityController.pause().stop().destroy();
    }

    @Test
    public void test_OnCreate() {
        mainActivityActivityController.create();
        assertNotNull(ReflectionHelpers.getField(mainActivityActivityController.get(), TOOLBAR_FIELD_NAME));
        assertNotNull(ReflectionHelpers.getField(mainActivityActivityController.get(), DRAWER_LAYOUT_FIELD_NAME));
    }

}