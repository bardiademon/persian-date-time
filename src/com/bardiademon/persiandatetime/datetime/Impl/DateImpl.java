package com.bardiademon.persiandatetime.datetime.Impl;

import com.bardiademon.persiandatetime.datetime.DateTime;

public final class DateImpl implements DateTime.Date
{
    private int year, month, day;
    private String dayName;
    private String monthName;

    @Override
    public int getYear()
    {
        return year;
    }

    @Override
    public int getMonth()
    {
        return month;
    }

    @Override
    public int getDay()
    {
        return day;
    }

    @Override
    public String getDayName()
    {
        return dayName;
    }

    @Override
    public String getMonthName()
    {
        return monthName;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public void setDayName(String dayName)
    {
        this.dayName = dayName;
    }

    public void setMonthName(String monthName)
    {
        this.monthName = monthName;
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %s %s" , getDayName() , getDay() , getMonthName() , getYear());
    }
}
