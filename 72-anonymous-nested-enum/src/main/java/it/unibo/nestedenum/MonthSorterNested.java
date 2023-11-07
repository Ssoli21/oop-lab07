package it.unibo.nestedenum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    /**
     * Enum class that contains the months of the year (plus their days) and a method {@link fromString}
     * to obtain a month from the enum given a string that properly represents it.
     */
    public enum Months{
        GENNAIO(31), FEBBRAIO(28), MARZO(31), APRILE(30), MAGGIO(31), GIUGNO(30), LUGLIO(31), AGOSTO(31),
        SETTEMBRE(30), OTTOBRE(31), NOVEMBRE(30), DICEMBRE(31);

        private final int days;

        Months(final int days){
            this.days = days;
        }

        public static Months fromString(final String monthName){
            String month = Objects.requireNonNull(monthName);
            try {
                return Months.valueOf(month);
            } catch (IllegalArgumentException e) {
                Months result = null;
                for (Months m : values()) {
                    if(m.toString().startsWith(month.toUpperCase())){
                        if(result != null){
                            throw new IllegalArgumentException("the string:" + month + "matches with more than one month");
                        }
                        result = m;
                    }
                }
            if(result == null){
                throw new IllegalArgumentException("the string: " + month + "doesn't match with any month");
            }
            return result;
            }
        }
    }

    /**
     * Istance of a comparator of strings that compares the days of two months 
     * represented by the given strings
     */
    private static final Comparator<String> DAYS = new Comparator<String>() {
        public int compare(String a, String b){
            Months m1 = Months.fromString(a);
            Months m2 = Months.fromString(b);
            return Integer.compare(m1.days, m2.days);
        }
    }; 

    /**
     * Istance of a comparator of strings that compares the order (chronological) of two months 
     * represented by the given strings
     */
    private static final Comparator<String> ORDER = new Comparator<String>() {
        public int compare(String a, String b){
            return Months.fromString(a).compareTo(Months.fromString(b));
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Comparator<String> sortByDays() {
        return DAYS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comparator<String> sortByOrder() {
        return ORDER;
    }
}
