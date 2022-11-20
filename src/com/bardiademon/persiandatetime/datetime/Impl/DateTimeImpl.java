package com.bardiademon.persiandatetime.datetime.Impl;

import com.bardiademon.persiandatetime.datetime.DateTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class DateTimeImpl implements DateTime
{
    private Time time;
    private Date jalaliDate, persianDate;

    @Override
    public Time getTime()
    {
        return time;
    }

    @Override
    public Date getJalaliDate()
    {
        return jalaliDate;
    }

    @Override
    public Date getIranDate()
    {
        return persianDate;
    }

    public void setTime(Time time)
    {
        this.time = time;
    }

    public void setJalaliDate(Date date)
    {
        this.jalaliDate = date;
    }

    public void setPersianDate(Date persianDate)
    {
        this.persianDate = persianDate;
    }

    @Override
    public String formatter(final String pattern)
    {
        return formatter(new SimpleDateFormat(pattern));
    }

    @Override
    public String formatter(final SimpleDateFormat simpleDateFormat)
    {
        return simpleDateFormat.format(getDate());
    }

    @Override
    public LocalDateTime getLocalDateTime()
    {
        final Date iranDate = getIranDate();
        final Time time = getTime();

        return LocalDateTime.of(iranDate.getYear() , iranDate.getMonth() , iranDate.getDay() ,
                time.getHour() , time.getMinutes() , time.getSeconds());
    }

    @Override
    public java.util.Date getDate()
    {
        return new java.util.Date(Timestamp.valueOf(getLocalDateTime()).getTime());
    }

    public String toStringPersianDateTime()
    {
        return String.format("%s - %s" , getIranDate().toString() , getTime().toString());
    }

    public String toStringJalaliDateTime()
    {
        return String.format("%s - %s" , getJalaliDate().toString() , getTime().toString());
    }

    @Override
    public String toString()
    {
        return toStringPersianDateTime();
    }
}
