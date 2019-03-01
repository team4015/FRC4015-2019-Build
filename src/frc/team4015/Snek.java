package frc.team4015;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

public class Snek implements Runnable {

    private static Random random = new Random();

    private int headX = 5;
    private int headY = 5;
    private int foodX = random.nextInt(30);
    private int foodY = random.nextInt(15);
    private int score = 0;
    private boolean active = true;

    private Direction direction = Direction.RIGHT;

    enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    class GridCell {

        private int expirationFrames = -1;
        private boolean active = false;

        private void activate(int durationFrames) {
            active = true;
            expirationFrames = durationFrames;
        }

        private void tick() {

            expirationFrames--;
            if (expirationFrames <= 0) {
                active = false;
            }

        }

    }

    private GridCell[][] gameBoard = new GridCell[30][15];

    public Snek() {
        int y = 0;
        for (GridCell[] row : gameBoard) {
            int x = 0;
            for (GridCell cell : row) {
                gameBoard[x][y] = new GridCell();
                x++;
            }
            y++;
        }
    }

    @Override
    public void run() {
/*
        try {
            if (Math.abs(OI.JoyStickRight.getX()) > Math.abs(OI.JoyStickRight.getY())) {
                if (OI.JoyStickRight.getX() > 0) {
                    direction = Direction.RIGHT;
                } else {
                    direction = Direction.LEFT;
                }
            } else {
                if (OI.JoyStickRight.getY() > 0) {
                    direction = Direction.UP;
                } else {
                    direction = Direction.DOWN;
                }
            }

            int y = 0;
            for (GridCell[] row : gameBoard) {
                int x = 0;
                for (GridCell cell : row) {
                    gameBoard[x][y].tick();
                    x++;
                }
                y++;
            }

            int targetX = headX;
            int targetY = headY;
            boolean successful = false;
            for (int reTest = 0; reTest < 2; reTest++) {
                if (!successful) {
                    if (direction.equals(Direction.RIGHT)) {
                        if (headX + 1 < 15) {
                            targetX = headX + 1;
                            successful = true;
                        } else {
                            direction = Direction.DOWN;
                        }
                    }
                    if (direction.equals(Direction.DOWN)) {
                        if (headY - 1 >= 0) {
                            targetY = headY - 1;
                            successful = true;
                        } else {
                            direction = Direction.LEFT;
                        }
                    }
                    if (direction.equals(Direction.LEFT)) {
                        if (headX - 1 >= 0) {
                            targetX = headX - 1;
                            successful = true;
                        } else {
                            direction = Direction.UP;
                        }
                    }
                    if (direction.equals(Direction.DOWN)) {
                        if (headY - 1 >= 0) {
                            targetY = headY - 1;
                            successful = true;
                        } else {
                            direction = Direction.RIGHT;
                        }
                    }
                }
            }
            if (targetX == foodX && targetY == foodY) {
                score++;
                replaceFood();
            }
            if (gameBoard[targetX][targetY].active) {
                endGame();
            }
            if (active) {
                gameBoard[headX][headY].activate(score);
                headX = targetX;
                headY = targetY;
                gameBoard[headX][headY].activate(score + 1);
            }
        } catch (ConcurrentModificationException e) {
            endGame();
        }
        */
    }

    public void endGame() {
        active = false;
    }

    private void replaceFood() {
        List<Integer> safeY = new ArrayList<>();
        int possible = 0;
        for (GridCell[] row : gameBoard) {
            boolean safe = false;
            for (GridCell cell : row) {
                if (!cell.active) {
                    safe = true;
                    break;
                }
            }
            if (safe) {
                safeY.add(possible);
            }
            possible++;
        }
        if (!safeY.isEmpty()) {
            int randY = safeY.get(random.nextInt(safeY.size()));
            List<Integer> safeX = new ArrayList<>();
            possible = 0;
            for (GridCell cell : gameBoard[randY]) {
                if (!cell.active) {
                    safeX.add(possible);
                }
                possible++;
            }
            int randX = safeX.get(random.nextInt(safeX.size()));
            foodX = randX;
            foodY = randY;
        }
        else {
            // TODO you win!
        }
    }

}
