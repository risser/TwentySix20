package com.twentysix20.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class DateUtil
{

    public static final String TODAY = "today";
    public static final long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
    public static final Date ALL_NINES_DATE = new Date(253636804800000L); // If you parse the date

    // '99999999', this is
    // the Date you end up
    // with.

    public static Date dateOnly(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date createDateTime(int month, int day, int year, int hour, int min, int sec)
    {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, sec);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date createDate(int month, int day, int year)
    {
        return createDateTime(month, day, year, 0, 0, 0);
    }

    public static Date addToDate(Date date, int unit, int num)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(unit, num);

        return c.getTime();
    }

    public static Date calculateRelativeDate(String value)
    {
        if (value == null)
            throw new IllegalArgumentException("Can't calculate date from null value.");

        value = value.toLowerCase();
        Date theDate = DateUtil.dateOnly(new Date());

        if (TODAY.equals(value) || StringUtil.isEmpty(value))
            return theDate;

        return calculateRelativeDate(theDate, value);
    }

    public static Date today()
    {
        return calculateRelativeDate(TODAY);
    }

    public static Date calculateRelativeDate(Date theDate, String value)
    {
        if (StringUtil.isEmpty(value))
            throw new IllegalArgumentException("Can't calculate date from null or blank value.");
        if (theDate == null)
            throw new IllegalArgumentException("Can't calculate date from null value.");
        value = value.toLowerCase();

        if (!value.matches("[\\+\\-][0-9]+[dmy]?"))
            throw new IllegalArgumentException(
                    "Value for relative date must be prefixed with a '+' or '-' and followed by a 'd', 'm' or 'y'.");

        char p = value.charAt(0);
        char e = value.charAt(value.length() - 1);
        if ( (e < '0') || (e > '9'))
            value = value.substring(0, value.length() - 1);
        else
            e = 'd'; // if it's not specified, assume it's days

        if (p == '+')
            value = value.substring(1);

        int delta = Integer.valueOf(value).intValue();

        Date newDate = null;
        switch (e)
        {
            case 'd':
                newDate = DateUtil.addToDate(theDate, Calendar.DAY_OF_YEAR, delta);
                break;
            case 'm':
                newDate = DateUtil.addToDate(theDate, Calendar.MONTH, delta);
                break;
            case 'y':
                newDate = DateUtil.addToDate(theDate, Calendar.YEAR, delta);
                break;
        }

        return newDate;
    }

    /**
     * This method will test to see if these dates are on the same day. There is a need for this
     * because there instances where we are interested in knowing if someone was born on the same
     * day as someone else (matching losses). Date class will compare for equality using
     * milliseconds, this unfortunately is a precision that is not very practical for the level of
     * comparison we require.
     * 
     * @param a - The Date to compare
     * @param b - The Date to compare
     * @return
     */
    public static boolean isSameDay(Date a, Date b)
    {
        if ( (a == null) && (b == null))
            return true;

        boolean isSameDay = false;

        if (a != null && b != null)
        {

            Calendar c = Calendar.getInstance();
            c.setTime(a);

            Calendar c1 = Calendar.getInstance();
            c1.setTime(b);

            isSameDay = ( (c.get(Calendar.DAY_OF_YEAR) == c1.get(Calendar.DAY_OF_YEAR)) && (c
                    .get(Calendar.YEAR) == c1.get(Calendar.YEAR)));
        }

        return isSameDay;
    }

    public static Date getBasicDate(int year, int month, int day)
    {
        return (new GregorianCalendar(year, month - 1, day)).getTime();
    }

    public static Date getDateOnly(Date d)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(d);
        return (new GregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc
                .get(Calendar.DATE))).getTime();
    }

    public static String formatDate(Date d, String pattern)
    {
        return StringUtil.formatDate(d, pattern);
    }

    public static Date parseDate(String source, String pattern) throws ParseException
    {
        if (source == null)
            throw new IllegalArgumentException("Source string cannot be null");
        if (pattern == null)
            throw new IllegalArgumentException("Pattern cannot be null");
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(source);
    }

    public static Date getEarliestDate(Date[] dates)
    {
        if ( (dates == null) || (dates.length == 0))
            throw new IllegalArgumentException("Date array cannot be null or empty.");
        return getEarliestDate(Arrays.asList(dates));
    }

    public static Date getEarliestDate(List dateList)
    {
        if ( (dateList == null) || (dateList.size() == 0))
            throw new IllegalArgumentException("Date array cannot be null or empty.");
        Date early = null;
        Iterator dates = dateList.iterator();
        while (dates.hasNext())
        {
            Object o = dates.next();
            if (o != null)
            {
                if (! (o instanceof Date))
                    throw new IllegalArgumentException("All elements in list must be dates.");
                Date date = (Date) o;
                if ( (early == null) || (date.before(early)))
                    early = date;
            }
        }
        return early;
    }

    public static Date getLatestDate(Date[] dates)
    {
        if ( (dates == null) || (dates.length == 0))
            throw new IllegalArgumentException("Date array cannot be null or empty.");
        return getLatestDate(Arrays.asList(dates));
    }

    public static Date getLatestDate(List dateList)
    {
        if ( (dateList == null) || (dateList.size() == 0))
            throw new IllegalArgumentException("Date array cannot be null or empty.");
        Date late = null;
        Iterator dates = dateList.iterator();
        while (dates.hasNext())
        {
            Object o = dates.next();
            if (o != null)
            {
                if (! (o instanceof Date))
                    throw new IllegalArgumentException("All elements in list must be dates.");
                Date date = (Date) o;
                if ( (late == null) || (date.after(late)))
                    late = date;
            }
        }
        return late;
    }

    /**
     * Calculates the absolute number of days between two dates, ignoring the time values. Valid
     * differences are zero or positive. Negative return for null or errors.
     * 
     * note: only accurate for modern dates (e.g 1600's or later).
     * 
     * @param start
     * @param end
     * @return
     */
    public static long deltaDays(Date start, Date end)
    {
        if (start != null && end != null)
        {
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(start);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(end);
            return deltaDays(startCalendar, endCalendar);
        }
        else
        {
            return -1;
        }
    }

    /**
     * Calculates the absolute number of days between two dates, ignoring the time values. Valid
     * differences are zero or positive. Negative return for null or errors.
     * 
     * note: only accurate for modern dates (e.g 1600's or later).
     * 
     * @param start
     * @param end
     * @return
     */
    public static long deltaDays(Calendar start, Calendar end)
    {
        return daysSinceEpoch(end) - daysSinceEpoch(start);
    }

    /**
     * Return the number of days since 0 AD.
     * 
     * @param day
     * @return
     */
    public static long daysSinceEpoch(Calendar day)
    {
        int year = day.get(Calendar.YEAR);
        int month = day.get(Calendar.MONTH);
        int daysThisYear = cumulDaysToMonth[month] + day.get(Calendar.DAY_OF_MONTH) - 1;
        if ( (month > 1) && isLeapYear(year))
        {
            daysThisYear++;
        }

        return daysToYear(year) + daysThisYear;
    }

    /**
     * Return whether the year argument (e.g. 2008) is a leap year
     * 
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year)
    {
        return (year % 400 == 0) || ( (year % 100 != 0) && (year % 4 == 0));
    }

    /**
     * How many days have passed since 0 AD up until, but not including January 1st of the "year"
     * argument.
     * 
     * @param year
     * @return
     */
    static int daysToYear(int year)
    {
        return (365 * year) + numLeapsToYear(year);
    }

    /**
     * How many leap years have their been between 0 AD and this year, including this year
     * 
     * @param year
     * @return
     */
    static int numLeapsToYear(int year)
    {
        int num4y = (year - 1) / 4;
        int num100y = (year - 1) / 100;
        int num400y = (year - 1) / 400;
        return num4y - num100y + num400y;
    }

    private static final int[] cumulDaysToMonth =
        {
                0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334
        };

    /**
     * 
     * @param start
     * @param end
     * @return the number of if the leap days (February 29th) that exists between the two dates
     */
    public static int getLeapDaysInRange(Date start, Date end)
    {

        // verify that start is before end
        if (start.after(end))
        {
            throw new IllegalArgumentException("start must be before end");
        }

        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(start);
        GregorianCalendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(end);

        GregorianCalendar leapDay = new GregorianCalendar();
        leapDay.set(Calendar.MONTH, Calendar.FEBRUARY);
        leapDay.set(Calendar.DAY_OF_MONTH, 29);

        int startYear = startCalendar.get(Calendar.YEAR);
        int endYear = endCalendar.get(Calendar.YEAR);
        int leapDays = 0;
        // loop over each year, inclusive of the start and end years,
        // and count the number of leap years where February 29th is
        // after the start date and before the end date.
        for (int currentYear = startYear; currentYear <= endYear; currentYear++)
        {
            if (leapDay.isLeapYear(currentYear))
            {
                // the current year is a leap year . Verify that february 29 of
                // current year is in the start/end range
                leapDay.setTime(getBasicDate(currentYear, 2, 29));
                if (!startCalendar.after(leapDay) && !endCalendar.before(leapDay))
                {
                    leapDays++;
                }
            }
        }

        return leapDays;

    }

    /**
     * Returns the number of months between two dates. The day of the month is not used in this
     * calculation.
     */
    public static int monthsBetween(Date date1, Date date2)
    {
        if ( (date1 == null) || (date2 == null))
            throw new NullPointerException("Can't call monthsBetween with a null date.");
        if (date1.after(date2))
        {
            Date temp = date1;
            date1 = date2;
            date2 = temp;
        }

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return 12 * (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR))
                + (calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH));
    }
}