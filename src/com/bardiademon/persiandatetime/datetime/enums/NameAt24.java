package com.bardiademon.persiandatetime.datetime.enums;

public enum NameAt24
{
    MORNING(Color.MDI_WEATHER_SUNSET_UP), NOON(Color.MDI_WHITE_BALANCE_SUNNY), AFTERNOON(Color.MDI_WHITE_BALANCE_SUNNY),
    EVENING(Color.MDI_WEATHER_SUNSET_DOWN), NIGHT(Color.MDI_WEATHER_NIGHT);

    private final Color font;

    NameAt24(final Color font)
    {
        this.font = font;
    }

    public Color getFont()
    {
        return font;
    }
}
