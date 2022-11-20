package com.bardiademon.persiandatetime;

import com.bardiademon.persiandatetime.datetime.Convertor;
import com.bardiademon.persiandatetime.datetime.DateTime;
import com.bardiademon.persiandatetime.datetime.convert.DateTimeConvertor;

public class Main
{
    public static void main(final String[] args)
    {
        System.out.println("bardiademon");

        final Convertor convertor = DateTimeConvertor.convertor();
        final DateTime now = convertor.now();
        final DateTime.Time time = now.getTime();

        System.out.printf("%s\n%s %s\n%s\n%s %s\n" ,

                time , time.getNameAt24().getPersianName() ,
                now.formatter("hh:mm:ss") ,

                now.formatter("hh:mm:ss aaa") ,

                now.formatter("hh:mm:ss") ,
                time.getTimett()
        );

        System.out.println("==============");

        final DateTime mahsaAminiBirth = convertor.of(2000 , 9 , 20);
        final DateTime mahsaAminiKilled = convertor.of(2022 , 9 , 16);

        System.out.printf("\uD83D\uDC7C\uD83C\uDFFB: %s\n" , mahsaAminiBirth.getDate());
        System.out.printf("\uD83D\uDE14: %s\n" , mahsaAminiKilled.getDate());
        System.out.println("==============");
        System.out.printf("\uD83D\uDC7C\uD83C\uDFFB: %s\n" , mahsaAminiBirth.getJalaliDate());
        System.out.printf("\uD83D\uDE14: %s\n" , mahsaAminiKilled.getJalaliDate());
    }
}