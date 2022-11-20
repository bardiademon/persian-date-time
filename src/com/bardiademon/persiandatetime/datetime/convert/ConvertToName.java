package com.bardiademon.persiandatetime.datetime.convert;

import java.util.Locale;

final class ConvertToName
{
    public static String convertDayNameToPersian(final String name)
    {
        switch (name.toLowerCase(Locale.ROOT))
        {
            case "sat":
            case "saturday":
                return "شنبه";
            case "sun":
            case "sunday":
                return "یک شنبه";
            case "mon":
            case "monday":
                return "دو شنبه";
            case "tue":
            case "tuesday":
                return "سه شنبه";
            case "wed":
            case "wednesday":
                return "چهار شنبه";
            case "thu":
            case "thursday":
                return "پنج شنبه";
            case "fri":
            case "friday":
                return "جمعه";
            default:
                return "null";
        }

    }

    public static String convertMonthNameToPersian(final int monthNumber)
    {
        switch (monthNumber)
        {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";
            default:
                return "null";
        }

    }
}
