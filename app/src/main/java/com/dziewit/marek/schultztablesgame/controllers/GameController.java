package com.dziewit.marek.schultztablesgame.controllers;

import com.dziewit.marek.schultztablesgame.controllers.utils.GameTimer;
import com.dziewit.marek.schultztablesgame.custom.utils.TableCellRunable;
import com.dziewit.marek.schultztablesgame.custom.views.ShultzTableView;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import java.util.List;
import java.util.Random;

/**
 * Created by Marek Dziewit
 * on 14.09.2016.
 */
public class GameController {
    protected ShultzTableView gameView;

    protected GameTimer gameTimer = new GameTimer();

    protected Integer lowestX, lowestY;


    public void startGame() {
        prepareTableForGame();
        gameTimer.start();
    }

    public void resumeGame() {
        gameTimer.resume();
    }

    public void pauseGame() {
        gameTimer.pause();
    }

    public void stopGame() {
        gameTimer.stop();
        gameView.clearValues();
    }


    protected void prepareTableForGame() {
        int maxNumber = gameView.getColumnsCount() * gameView.getColumnsCount();
        final List<Integer> gameValues = ContiguousSet.
                create(Range.closed(1, maxNumber), DiscreteDomain.integers()).asList();

        gameView.cellIterator(new TableCellRunable() {
            @Override
            public void run(int row, int column) {
                Integer value = gameValues.remove(new Random().nextInt(gameValues.size()));
                gameView.setCellValue(column, row, value);
            }
        });
    }

    /*
     * BUILDER OF GAME CONTROLLER CLASS
     */
    private GameController(Builder builder) {
        gameView = builder.gameView;
    }

    public static class Builder {
        private ShultzTableView gameView;

        public Builder gameView(ShultzTableView tableView) {
            this.gameView = tableView;
            return this;
        }

        public GameController build() {
            return new GameController(this);
        }
    }
}
