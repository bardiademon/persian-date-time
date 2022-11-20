package com.bardiademon.persiandatetime.datetime.Impl;

import com.bardiademon.persiandatetime.datetime.DateTime;
import com.bardiademon.persiandatetime.datetime.enums.NameAt24;
import com.bardiademon.persiandatetime.datetime.enums.Timett;

public class TimeImpl implements DateTime.Time
{
    private int hour, minutes, seconds;

    private NameAt24 nameAt24;

    @Override
    public int getHour()
    {
        return hour;
    }

    @Override
    public int getMinutes()
    {
        return minutes;
    }

    @Override
    public int getSeconds()
    {
        return seconds;
    }

    @Override
    public NameAt24 getNameAt24()
    {
        return nameAt24;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    @Override
    public Timett getTimett()
    {
        return (hour < 12 ? Timett.AM : Timett.PM);
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
    }

    public void setNameAt24(NameAt24 nameAt24)
    {
        this.nameAt24 = nameAt24;
    }

    public String toString()
    {
        return String.format("%s:%s:%s" , to10(getHour()) , to10(getMinutes()) , to10(getSeconds()));
    }

    private String to10(final Object value)
    {
        return ((((int) value) >= 10) ? String.valueOf(value) : "0" + value);
    }
}
