package com.nightstalker.account;

/**
 * The enum Month.
 */
public enum Month {
    /**
     * January month.
     */
    January,
    /**
     * February month.
     */
    February,
    /**
     * Mars month.
     */
    Mars,
    /**
     * April month.
     */
    April,
    /**
     * May month.
     */
    May,
    /**
     * June month.
     */
    June,
    /**
     * July month.
     */
    July,
    /**
     * August month.
     */
    August,
    /**
     * September month.
     */
    September,
    /**
     * October month.
     */
    October,
    /**
     * November month.
     */
    November,
    /**
     * December month.
     */
    December;

    /**
     * Gets day.
     *
     * @param year the year
     * @return the day
     */
    public int getDay(int year) {
        boolean isLeapYear = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);

        return switch (this) {
            case January, Mars, May, July, August, October, December  -> 31;
            case April, June, September, November -> 30;
            case February -> isLeapYear ? 29 : 28;
        };
    }
}
