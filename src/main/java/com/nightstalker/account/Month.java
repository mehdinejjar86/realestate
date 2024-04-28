package com.nightstalker.account;

public enum Month {
    January,
    February,
    Mars,
    April,
    May,
    June,
    July,
    August,
    September,
    October,
    November,
    December;

    public int getDay(int year) {
        boolean isLeapYear = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);

        return switch (this) {
            case January, Mars, May, July, August, October, December  -> 31;
            case April, June, September, November -> 30;
            case February -> isLeapYear ? 29 : 28;
        };
    }
}
