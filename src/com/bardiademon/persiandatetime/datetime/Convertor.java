package com.bardiademon.persiandatetime.datetime;

import java.time.LocalDateTime;
import java.util.Date;

public interface Convertor
{
    DateTime now() throws NullPointerException;

    DateTime of(final int year , final int month , final int dayOfMonth) throws NullPointerException;

    DateTime of(final int year , final int month , final int dayOfMonth , final int hour , final int minute , final int second) throws NullPointerException;

    DateTime of(final LocalDateTime localDateTime) throws NullPointerException;

    DateTime of(final Date date) throws NullPointerException;
}
