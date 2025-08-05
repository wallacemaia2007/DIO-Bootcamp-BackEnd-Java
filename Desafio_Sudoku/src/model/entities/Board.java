package model.entities;

import java.util.Collection;
import java.util.List;

import model.entities.enums.GameStatusEnum;

import static model.entities.enums.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatus() {
        var allEmpty = spaces.stream()
                .flatMap(Collection::stream)
                .noneMatch(s -> !s.isFixed() && nonNull(s.getActual()));

        if (allEmpty) return NON_STARTED;

        var hasEmpty = spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> isNull(s.getActual()));

        return hasEmpty ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == NON_STARTED) {
            return false;
        }

        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value) {
        if (!isValidPosition(col, row)) {
            System.out.println("Posição inválida!");
            return false;
        }
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.setActual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row) {
        if (!isValidPosition(col, row)) {
            System.out.println("Posição inválida!");
            return false;
        }
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.clearSpace();
        return true;
    }

    public void reset() {
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished() {
        return !hasErrors() && getStatus().equals(COMPLETE);
    }

    private boolean isValidPosition(int col, int row) {
        return col >= 0 && col < spaces.size() && row >= 0 && row < spaces.get(0).size();
    }
}
