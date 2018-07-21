package com.krokoklemme.shrekdroid;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a category with interoperable units
 *
 * @see Unit
 */
public final class Category {
    private String name;
    private final List<Unit> units = new ArrayList<>();

    /**
     * Constructs a new category with the given name
     *
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = String.valueOf(name);
    }

    /**
     * Retrieves the currently assigned name
     * for this category
     *
     * @return the name of the category
     */
    @Contract(pure = true)
    public String getName() {
        return name;
    }

    /**
     * Gets the list of units for this category
     *
     * @return a list of units
     */
    @Contract(pure = true)
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * Sets the name of the category to the specified value
     *
     * @param name the new name of the category
     */
    public void setName(String name) {
        this.name = String.valueOf(name);
    }

    /**
     * Adds a new unit to the current category
     *
     * @param name name of the new unit
     * @param symbol symbol of the new unit
     * @param value value of the new unit in shreks
     */
    public void addUnit(String name, String symbol, double value) {
        new Unit(name, symbol, value);
    }

    /**
     * Returns the current name of the category
     *
     * @return the name of the category
     */
    @Override
    @Contract(pure = true)
    public String toString() {
        return getName();
    }

    /**
     * Represents a unit of measurement
     *
     * @see Category
     */
    public final class Unit {
        private String name;
        private String symbol;
        private double value;

        /**
         * Defines a new unit and adds it to the parent category
         *
         * @param name name of the unit
         * @param symbol unit-symbol
         * @param value value of unit in shreks
         */
        public Unit(String name, String symbol, double value) {
            this.name = name;
            this.symbol = symbol;
            this.value = value;

            Category.this.units.add(this);
        }

        /**
         * Gets the name of the unit
         *
         * @return name of the unit
         */
        @Contract(pure = true)
        public String getName() {
            return name;
        }

        /**
         * Gets the symbol of the unit
         *
         * @return unit symbol
         */
        @Contract(pure = true)
        public String getSymbol() {
            return symbol;
        }

        /**
         * Gets the value of this unit, in shreks
         *
         * @return unit value in shreks
         */
        @Contract(pure = true)
        public double getValue() {
            return value;
        }

        /**
         * Retrieves the category this unit belongs to
         *
         * @return parent category
         * @see Category
         */
        @Contract(pure = true)
        public Category getCategory() {
            return Category.this;
        }

        /**
         * Replaces the current name with the specified one
         *
         * @param name new name
         */
        public void setName(String name) {
            this.name = String.valueOf(name);
        }

        /**
         * Replaces the current symbol with the specified one
         *
         * @param symbol new symbol
         */
        public void setSymbol(String symbol) {
            this.symbol = String.valueOf(symbol);
        }

        /**
         * Reassigns the specified value to the unit
         *
         * @param value new value, in shreks
         */
        public void setValue(double value) {
            this.value = value;
        }

        /**
         * Returns the current name of the unit
         *
         * @return current unit name
         */
        @Override
        @Contract(pure = true)
        public String toString() {
            return getName();
        }
    }
}
