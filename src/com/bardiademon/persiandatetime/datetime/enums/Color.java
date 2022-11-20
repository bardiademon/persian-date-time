package com.bardiademon.persiandatetime.datetime.enums;

public enum Color
{
    MDI_WHITE_BALANCE_SUNNY("#C70039"),
    MDI_WEATHER_NIGHT("#212121"),
    MDI_WEATHER_SUNSET_UP("#FF5733"),
    MDI_WEATHER_SUNSET_DOWN("#FF5733");

    private final String color;

    Color(final String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }
}
