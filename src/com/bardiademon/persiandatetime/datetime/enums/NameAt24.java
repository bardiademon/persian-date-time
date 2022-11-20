package com.bardiademon.persiandatetime.datetime.enums;

public enum NameAt24
{
    MORNING("صبح" , Color.MDI_WEATHER_SUNSET_UP),
    NOON("ظهر" , Color.MDI_WHITE_BALANCE_SUNNY),
    AFTERNOON("بعد از ظهر" , Color.MDI_WHITE_BALANCE_SUNNY),
    EVENING("عصر" , Color.MDI_WEATHER_SUNSET_DOWN),
    NIGHT("شب" , Color.MDI_WEATHER_NIGHT);

    private final String persianName;
    private final Color font;

    NameAt24(final String persianName , final Color font)
    {
        this.persianName = persianName;
        this.font = font;
    }

    public Color getFont()
    {
        return font;
    }

    public String getPersianName()
    {
        return persianName;
    }
}
