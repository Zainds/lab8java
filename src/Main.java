import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public enum Day {

        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }
    public static class MyTime
    {
        private int hourTime;
        private int minuteTime;
        private int secondTime;
        private Day curDay;
        private Calendar calendar;

        public MyTime() {};
        public MyTime(int h, int m, int s, Day d, int year, int mon, int day) throws ExceptionInInitializerError {
            if (s < 0 || s > 59 || m < 0 || m > 59 || h < 0 || h > 23) {
                throw new ExceptionInInitializerError("Invalid data") ;
            }

            this.hourTime = h;
            this.minuteTime = m;
            this.secondTime = s;
            this.curDay = d;
            this.calendar = new GregorianCalendar(year, mon, day);
        }

        public int GetAllInSeconds(){
            return hourTime * 60 * 60 + minuteTime * 60 + secondTime;
        }
        public void Add5Seconds() {
            secondTime += 5;
            if (secondTime >= 60) {
                minuteTime += 1;
                secondTime -= 60;
                if (minuteTime >= 60) {
                    hourTime += 1;
                    minuteTime -= 60;
                    if (hourTime >=24)
                    {
                        hourTime = 0;

                    }
                }
            }
        }
        public void SetHours(int hours) {
            hourTime = hours;
        }
        public void SetMinutes(int minutes) {
            minuteTime = minutes;
        }
        public void SetSeconds(int Seconds) {
            secondTime = Seconds;
        }

        public int GetHours()  {
            return hourTime;
        }

        public int GetMinutes()  {
            return minuteTime;
        }

        public int GetSeconds()  {
            return secondTime;
        }
        public void PrintTime() {
            String h, m, s, d;
            if (hourTime < 10) {
                h = "0" + String.valueOf(hourTime);
            }
            else {
                h = "" + String.valueOf(hourTime);
            }
            if (minuteTime < 10) {
                m = "0" + String.valueOf(minuteTime);
            }
            else {
                m = "" + String.valueOf(minuteTime);
            }
            if (secondTime < 10) {
                s = "0" + String.valueOf(secondTime);
            }
            else {
                s = "" + String.valueOf(secondTime);
            }
            d = curDay.name();
            int c_year = calendar.get(Calendar.YEAR);
            int c_month = calendar.get(Calendar.MONTH);
            int c_day = calendar.get(Calendar.DAY_OF_MONTH);
            System.out.println( h + ":" + m + ":" + s + " " + d + " " + c_year
            + " " + c_month + " " +c_day)  ;
        }

    };
    public static void main(String[] args) {

        MyTime T = new MyTime(23, 59, 50, Day.TUESDAY, 2019, 2, 2);
        MyTime T1 = new MyTime(20, 00, 11, Day.THURSDAY, 2025, 11, 5);
        MyTime T2 = new MyTime(05, 06, 11, Day.WEDNESDAY, 2016, 4, 16);
        MyTime T3 = new MyTime(12, 24, 13, Day.MONDAY, 2016, 2, 25);
        MyTime T4 = new MyTime(18, 45, 21, Day.FRIDAY, 2021, 8, 8);
        List<MyTime> times = Arrays.asList(T, T1, T2, T3, T4);

        System.out.println("Поиск по перечисляемому типу");
        for(MyTime it: times){
            if(it.curDay == Day.MONDAY)it.PrintTime();
        }
        System.out.println();
        Collections.sort(times, new Comparator<MyTime>() {
            @Override // сортировка по полю Calendar
            public int compare(MyTime o1, MyTime o2) {
                return o2.calendar.compareTo(o1.calendar);
            }

        });
        System.out.println("сортировка по полю Calendar");
        for(MyTime it: times){
            it.PrintTime();
        }
    }
}