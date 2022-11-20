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
    public Date getDate()
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
        return simpleDateFormat.format(toDate());
    }

    @Override
    public String formatterJalali(String pattern)
    {
        return formatterJalali(new SimpleDateFormat(pattern));
    }

    @Override
    public String formatterJalali(SimpleDateFormat simpleDateFormat)
    {
        return simpleDateFormat.format(toJalaliDate());
    }

    @Override
    public LocalDateTime getLocalDateTime()
    {
        final Date persianDate = getDate();
        final Time time = getTime();

        return LocalDateTime.of(persianDate.getYear() , persianDate.getMonth() , persianDate.getDay() ,
                time.getHour() , time.getMinutes() , time.getSeconds());
    }

    @Override
    public LocalDateTime getJalaliLocalDateTime()
    {
        final Date jalaliDate = getJalaliDate();
        final Time time = getTime();

        return LocalDateTime.of(jalaliDate.getYear() , jalaliDate.getMonth() , jalaliDate.getDay() ,
                time.getHour() , time.getMinutes() , time.getSeconds());
    }

    @Override
    public java.util.Date toDate()
    {
        return new java.util.Date(Timestamp.valueOf(getLocalDateTime()).getTime());
    }

    @Override
    public java.util.Date toJalaliDate()
    {
        return new java.util.Date(Timestamp.valueOf(getJalaliLocalDateTime()).getTime());
    }

    public String toStringDateTime()
    {
        return String.format("%s - %s" , getDate().toString() , getTime().toString());
    }

    public String toStringJalaliDateTime()
    {
        return String.format("%s - %s" , getJalaliDate().toString() , getTime().toString());
    }

    @Override
    public String toString()
    {
        return toStringDateTime();
    }
}
