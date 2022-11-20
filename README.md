# persian-date-time
Persian Date Time

## Example

```java
package com.bardiademon.persiandatetime;

import com.bardiademon.persiandatetime.datetime.DateTime;
import com.bardiademon.persiandatetime.datetime.convert.DateTimeConvertor;

import java.time.LocalDateTime;

public class Main
{
    public static void main(final String[] args)
    {
        System.out.println("bardiademon");

        final DateTimeConvertor convertor = new DateTimeConvertor();

        final DateTime now = convertor.now();

        System.out.println(now.toStringPersianDateTime());
        System.out.println(now.toStringJalaliDateTime());

        System.out.println();

        final LocalDateTime localDateTime = LocalDateTime.of(2022 , 9 , 16 , 17 , 0 , 0);
        final DateTime convert = convertor.convert(localDateTime);

        System.out.println(convert.toStringPersianDateTime());
        System.out.println(convert.toStringJalaliDateTime());
    }
}
```

## Output
```
bardiademon
یک شنبه 29 آبان 2581 - 16:27:32
یک شنبه 29 آبان 1401 - 16:27:32

جمعه 25 شهریور 2581 - 17:00:00
جمعه 25 شهریور 1401 - 17:00:00
```
