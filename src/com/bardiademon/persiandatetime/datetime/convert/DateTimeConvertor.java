package com.bardiademon.persiandatetime.datetime.convert;

import com.bardiademon.persiandatetime.datetime.DateTime;
import com.bardiademon.persiandatetime.datetime.Impl.DateImpl;
import com.bardiademon.persiandatetime.datetime.Impl.DateTimeImpl;
import com.bardiademon.persiandatetime.datetime.Impl.TimeImpl;
import com.bardiademon.persiandatetime.datetime.enums.NameAt24;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public final class DateTimeConvertor
{
    private static final int KEY_YMD_PERSIAN = 0, KEY_YMD_JALALI = 1;

    public DateTime now() throws NullPointerException
    {
        return convert(new Date());
    }

    public DateTime convert(final LocalDateTime localDateTime) throws NullPointerException
    {
        return convert(Timestamp.valueOf(localDateTime));
    }

    public DateTime convert(final Date date) throws NullPointerException
    {
        if (date == null) throw new NullPointerException("Data is null");

        final DateTimeImpl dateTimeImpl = new DateTimeImpl();

        final Object[] time = getTime(date);

        final String dayName = ConvertToName.convertDayNameToPersian((String) time[3]);

        final DateImpl dateImpl = new DateImpl();
        final TimeImpl timeImpl = new TimeImpl();
        final DateImpl persianDateImpl = new DateImpl();

        final YMD[] ymds = convertDate(date);
        final YMD ymdPersian = ymds[KEY_YMD_PERSIAN];
        final YMD ymdJalali = ymds[KEY_YMD_JALALI];

        final String monthName = ConvertToName.convertMonthNameToPersian(ymdJalali.month());

        dateImpl.setYear(ymdJalali.year());
        dateImpl.setMonth(ymdPersian.month());
        dateImpl.setDay(ymdPersian.day());
        dateImpl.setDayName(dayName);
        dateImpl.setMonthName(monthName);

        persianDateImpl.setYear(ymdPersian.year());
        persianDateImpl.setMonth(ymdPersian.month());
        persianDateImpl.setDay(ymdPersian.day());
        persianDateImpl.setDayName(dayName);
        persianDateImpl.setMonthName(monthName);

        if (dateTimeImpl.getSolarDate() == null) dateTimeImpl.setSolarDate(dateImpl);
        if (dateTimeImpl.getIranDate() == null) dateTimeImpl.setPersianDate(persianDateImpl);

        timeImpl.setHour((int) time[0]);
        timeImpl.setMinutes((int) time[1]);
        timeImpl.setSeconds((int) time[2]);

        timeImpl.setNameAt24(getNameAt24(dateImpl.getMonth() , timeImpl.getHour()));

        if (dateTimeImpl.getTime() == null) dateTimeImpl.setTime(timeImpl);

        new Thread(System::gc).start();

        return dateTimeImpl;
    }

    private YMD[] convertDate(final Date date)
    {
        final SimpleDateFormat dateFormat = new SimpleDateFormat();

        dateFormat.applyLocalizedPattern("yyyy");
        final int year = Integer.parseInt(dateFormat.format(date));

        dateFormat.applyLocalizedPattern("M");
        final int month = Integer.parseInt(dateFormat.format(date));

        dateFormat.applyLocalizedPattern("d");
        final int day = Integer.parseInt(dateFormat.format(date));

        final YMD YMDPersian = gregorianToIranDate(year , month , day);
        final YMD YMDJalali = gregorianToJalali(year , month , day);

        final YMD[] ymd = new YMD[2];
        ymd[KEY_YMD_PERSIAN] = YMDPersian;
        ymd[KEY_YMD_JALALI] = YMDJalali;

        return ymd;
    }

    private NameAt24 getNameAt24(final int month , final int hour)
    {
        NameAt24 nameAt24 = null;

        if (hour >= 6 && hour < 12) nameAt24 = NameAt24.MORNING;
        else if (hour == 12 || hour == 13) nameAt24 = NameAt24.NOON;
        else if (hour >= 13 && hour < 15) nameAt24 = NameAt24.AFTERNOON;
        else
        {
            if (month >= 7 && month <= 11)
            {
                if (hour >= 15 && hour <= 17) nameAt24 = NameAt24.EVENING;
                else if (hour > 17 || hour <= 5) nameAt24 = NameAt24.NIGHT;
            }
            else
            {
                if (hour >= 15 && hour <= 19) nameAt24 = NameAt24.EVENING;
                else if (hour > 19 || hour <= 5) nameAt24 = NameAt24.NIGHT;
            }
        }

        return nameAt24;
    }

    private Object[] getTime(final Date date)
    {
        final SimpleDateFormat dateFormat = new SimpleDateFormat();

        dateFormat.applyLocalizedPattern("H");
        final int hour = Integer.parseInt(dateFormat.format(date));

        dateFormat.applyLocalizedPattern("m");
        final int min = Integer.parseInt(dateFormat.format(date));

        dateFormat.applyLocalizedPattern("s");
        final int sec = Integer.parseInt(dateFormat.format(date));

        dateFormat.applyLocalizedPattern("E");
        final String dayName = dateFormat.format(date);

        return new Object[]{hour , min , sec , dayName};
    }

    private YMD gregorianToJalali(int year , int month , int day)
    {
        int days, jalaliMonth, jalaliDay;
        {
            int gregorianYear2 = (month > 2) ? (year + 1) : year;
            int[] g_d_m = {0 , 31 , 59 , 90 , 120 , 151 , 181 , 212 , 243 , 273 , 304 , 334};
            days = 355666 + (365 * year) + ((gregorianYear2 + 3) / 4) - ((gregorianYear2 + 99) / 100) + ((gregorianYear2 + 399) / 400) + day + g_d_m[month - 1];
        }
        int jalaliYear = -1595 + (33 * (days / 12053));
        days %= 12053;
        jalaliYear += 4 * (days / 1461);
        days %= 1461;
        if (days > 365)
        {
            jalaliYear += (days - 1) / 365;
            days = (days - 1) % 365;
        }
        if (days < 186)
        {
            jalaliMonth = 1 + (days / 31);
            jalaliDay = 1 + (days % 31);
        }
        else
        {
            jalaliMonth = 7 + ((days - 186) / 30);
            jalaliDay = 1 + ((days - 186) % 30);
        }
        return new YMD(jalaliYear , jalaliMonth , jalaliDay);
    }

    private YMD jalaliToGregorian(int year , int month , int day)
    {
        year += 1595;
        int days = -355668 + (365 * year) + ((year / 33) * 8) + (((year % 33) + 3) / 4) + day + ((month < 7) ? (month - 1) * 31 : ((month - 7) * 30) + 186);
        int gregorianYear = 400 * (days / 146097);
        days %= 146097;
        if (days > 36524)
        {
            gregorianYear += 100 * (--days / 36524);
            days %= 36524;
            if (days >= 365) days++;
        }
        gregorianYear += 4 * (days / 1461);
        days %= 1461;
        if (days > 365)
        {
            gregorianYear += (days - 1) / 365;
            days = (days - 1) % 365;
        }
        int gregorianMonth, gregorianDay = days + 1;
        {
            int[] sal_a = {0 , 31 , ((gregorianYear % 4 == 0 && gregorianYear % 100 != 0) || (gregorianYear % 400 == 0)) ? 29 : 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31};
            for (gregorianMonth = 0; gregorianMonth < 13 && gregorianDay > sal_a[gregorianMonth]; gregorianMonth++)
                gregorianDay -= sal_a[gregorianMonth];
        }
        return new YMD(gregorianYear , gregorianMonth , gregorianDay);
    }

    private YMD gregorianToIranDate(int year , int month , int day)
    {
        final YMD YMD = gregorianToJalali(year , month , day);
        return new YMD(YMD.year() + 1180 , YMD.month() , YMD.day());
    }

    private static final class YMD
    {
        private final int year;
        private final int month;
        private final int day;

        private YMD(final int year , final int month , final int day)
        {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public String toString()
        {
            return String.format("%s-%s-%s" , year , month , day);
        }

        public int year()
        {
            return year;
        }

        public int month()
        {
            return month;
        }

        public int day()
        {
            return day;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            final YMD that = (YMD) obj;
            return this.year == that.year &&
                    this.month == that.month &&
                    this.day == that.day;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(year , month , day);
        }

    }

    @Override
    public String toString()
    {
        System.out.println("bardiademon");
        return now().toString();
    }
}
