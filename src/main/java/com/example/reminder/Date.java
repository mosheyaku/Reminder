package com.example.reminder;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Date class represents a date with day, month, and year.
 * It implements the Serializable interface to support object serialization.
 */
public class Date implements Serializable {
    private int day, year;
    private String month;

    /**
     * Constructs a Date object with the given day, month, and year.
     *
     * @param day   The day of the date.
     * @param month The month of the date.
     * @param year  The year of the date.
     */
    public Date(int day, String month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the day of the date.
     *
     * @return The day.
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of the date.
     *
     * @param day The day to set.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Returns the month of the date.
     *
     * @return The month.
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the month of the date.
     *
     * @param month The month to set.
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * Returns the year of the date.
     *
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the date.
     *
     * @param year The year to set.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Checks if this Date object is equal to the specified object.
     * Two Date objects are considered equal if they have the same day, month, and year.
     *
     * @param obj The object to compare against.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Date))
            return false;
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Date date = (Date) obj;
        return day == date.day && month.equals(date.month) && year == date.year;
    }

    /**
     * Generates a hash code for this Date object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
