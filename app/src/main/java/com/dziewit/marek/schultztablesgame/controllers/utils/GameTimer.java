package com.dziewit.marek.schultztablesgame.controllers.utils;

import com.google.common.base.Stopwatch;

/**
 * Created by Marek Dziewit
 * on 14.09.2016.
 */
public class GameTimer {

    Stopwatch stopwatch = Stopwatch.createUnstarted();

    public void start() {
        stopwatch.reset();
        stopwatch.start();
    }

    public void stop() {
        stopwatch.reset();
        stopwatch.stop();
    }

    public void resume() {
        stopwatch.start();
    }

    public void pause() {
        stopwatch.stop();
    }
}
