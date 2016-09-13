package com.dziewit.marek.schultztablesgame.custom.views;

import com.dziewit.marek.schultztablesgame.BuildConfig;
import com.dziewit.marek.schultztablesgame.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Marek Dziewit
 * on 12.09.2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ShultzTableViewTest {

    ShultzTableView tableViewMock;

    @Before
    public void setUp() throws Exception {
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create().visible().get();
        tableViewMock = spy(new ShultzTableView(activity));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRefreshView_CallRemoveAllViews() throws Exception {
        tableViewMock.refreshView(0, 0);

        verify(tableViewMock, times(1)).removeAllViews();
    }

    @Test
    public void testGenerateRow() throws Exception {

    }

    @Test
    public void testGenerateCell() throws Exception {

    }

    @Test
    public void testGetCellID() throws Exception {

    }

    @Test
    public void testGetRowID() throws Exception {

    }

    @Test
    public void testSetCellValue() throws Exception {

    }
}