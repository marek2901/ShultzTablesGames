package com.dziewit.marek.schultztablesgame.custom.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.dziewit.marek.schultztablesgame.R;


/**
 * Created by markdz
 * on 11.09.2016.
 */
public class ShultzTableView extends LinearLayout {

    public static final int DEFAULT_ROWS = 2;
    public static final int DEFAULT_COLUMNS = 2;

    public static final String DEFAULT_CELL_VALUE = "0";

    public static final String CELL_PREFIX = "C";
    public static final String ROW_PREFIX = "R";

    public ShultzTableView(Context context) {
        super(context);
        init();
    }

    public ShultzTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShultzTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShultzTableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.setOrientation(VERTICAL);
        refreshView(DEFAULT_ROWS, DEFAULT_COLUMNS);
    }

    public void refreshView(int rows, int columns) {
        this.removeAllViews();
        for (int rowIterator = 0; rowIterator < rows; rowIterator++) {
            LinearLayout row = generateRow(rowIterator);
            for (int columnIterator = 0; columnIterator < columns; columnIterator++) {
                View cell = generateCell(columnIterator);
                row.addView(cell);
            }
            this.addView(row);
        }
    }

    protected LinearLayout generateRow(int rowLevel) {
        LinearLayout row = new LinearLayout(getContext());
        row.setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
        row.setLayoutParams(params);
        row.setTag(getRowID(rowLevel));
        return row;
    }

    protected View generateCell(int childLevel) {
        AppCompatButton cell = new AppCompatButton(getContext());
        LinearLayout.LayoutParams params = new LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        cell.setLayoutParams(params);
        cell.setTag(getCellID(childLevel));
        cell.setText(DEFAULT_CELL_VALUE);
        cell.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        cell.setGravity(Gravity.CENTER);
        ViewCompat.setBackgroundTintList(cell,
                ContextCompat.getColorStateList(getContext(), R.color.colorPrimaryDark));
        cell.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        return cell;
    }

    protected String getCellID(int integerID) {
        return String.format("%s%s", CELL_PREFIX, integerID);
    }

    protected String getRowID(int integerID) {
        return String.format("%s%s", ROW_PREFIX, integerID);
    }

    public boolean setCellValue(int x, int y, int value) {
        LinearLayout row = (LinearLayout) this.findViewWithTag(getRowID(y));
        if (row != null) {
            AppCompatButton cell = (AppCompatButton) row.findViewWithTag(getCellID(x));
            if (cell != null) {
                cell.setText(String.valueOf(value));
                return true;
            }
        }
        return false;
    }
}
