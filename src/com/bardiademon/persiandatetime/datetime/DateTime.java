package com.bardiademon.persiandatetime.datetime;

import com.bardiademon.persiandatetime.datetime.enums.NameAt24;
import com.bardiademon.persiandatetime.datetime.enums.Timett;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public interface DateTime
{
    Time getTime();

    Date getSolarDate();

    Date getIranDate();

    String formatter(final String pattern);

    String formatter(final SimpleDateFormat pattern);

    java.util.Date getDate();

    LocalDateTime getLocalDateTime();

    String toStringSolarDateTime();

    String toStringIranDateTime();

    interface Date
    {
        int getYear();

        int getMonth();

        int getDay();

        String getDayName();

        String getMonthName();
    }

    interface Time
    {
        int getHour();

        int getMinutes();

        int getSeconds();

        NameAt24 getNameAt24();

        Timett getTimett();
    }
}
