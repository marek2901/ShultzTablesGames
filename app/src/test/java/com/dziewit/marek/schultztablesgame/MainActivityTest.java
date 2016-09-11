package com.dziewit.marek.schultztablesgame;

import android.view.Menu;

import com.dziewit.marek.schultztablesgame.custom.views.ShultzTableView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;
import org.robolectric.util.ReflectionHelpers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Marek Dziewit
 * on 11.09.2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    public static final String TOOLBAR_FIELD_NAME = "toolbar";
    public static final String DRAWER_LAYOUT_FIELD_NAME = "drawerLayout";
    public static final int MENU_ITEMS_COUNT = 6;
    public static final String TABLE_VIEW_FIELD_NAME = "tableView";

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

    @Test
    public void test_MenuCreation() throws Exception {
        MainActivity mainActivity = mainActivityActivityController.create().visible().get();

        Menu menu = shadowOf(mainActivity).getOptionsMenu();

        assertEquals(MENU_ITEMS_COUNT, menu.size());

        for (int i = 0; i < MENU_ITEMS_COUNT; i++) {
            assertEquals(String.format("%sx%s", i + 2, i + 2), menu.getItem(i).getTitle());
        }
    }

    @Test
    public void test_OnOptionItemSelected() throws Exception {
        MainActivity mainActivity = mainActivityActivityController.create().visible().get();
        ShultzTableView tableView = mock(ShultzTableView.class);

        ReflectionHelpers.setField(mainActivity, TABLE_VIEW_FIELD_NAME, tableView);

        shadowOf(mainActivity).clickMenuItem(0);

        verify(tableView, times(1)).refreshView(eq(2), eq(2));
    }
}