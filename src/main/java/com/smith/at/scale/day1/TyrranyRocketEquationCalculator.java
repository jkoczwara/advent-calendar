package com.smith.at.scale.day1;

import com.google.common.base.Preconditions;

class TyrranyRocketEquationCalculator implements RocketEquationCalculator {

    private final int divider;
    private final int substractor;

    public TyrranyRocketEquationCalculator() {
        this.divider = DEFAULT_DIVIDER;
        this.substractor = DEFAULT_SUBSTRACTOR;
    }

    public TyrranyRocketEquationCalculator(int divider, int substractor) {
        this.divider = divider;
        this.substractor = substractor;
    }

    // For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
    // Any mass that would require negative fuel should instead be treated as if it requires zero fuel;
    // the remaining mass, if any, is instead handled by wishing really hard, which has no mass and is outside the scope of this calculation.
    @Override
    public long calculateFor(long mass) {
        Preconditions.checkArgument(mass > 0, "Mass has to be positive integer.");

        int result = Math.floorDiv((int)mass, divider) - substractor;
        Preconditions.checkArgument(result > 0, "Negative mass does not make any sense.");

        return result;
    }

}
