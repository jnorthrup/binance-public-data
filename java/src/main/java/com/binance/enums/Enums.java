package com.binance.enums;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class Enums {

    public enum YEARS {
        Y2017, Y2018, Y2019, Y2020, Y2021, Y2022, Y2023, Y2024, Y2025
    }

    public enum INTERVALS {
        S1, M1, M3, M5, M15, M30, H1, H2, H4, H6, H8, H12, D1, D3, W1, MO1
    }

    public enum TRADING_TYPE {
        SPOT, UM, CM
    }

    public enum MONTHS {
        JANUARY(Month.JANUARY),
        FEBRUARY(Month.FEBRUARY),
        MARCH(Month.MARCH),
        APRIL(Month.APRIL),
        MAY(Month.MAY),
        JUNE(Month.JUNE),
        JULY(Month.JULY),
        AUGUST(Month.AUGUST),
        SEPTEMBER(Month.SEPTEMBER),
        OCTOBER(Month.OCTOBER),
        NOVEMBER(Month.NOVEMBER),
        DECEMBER(Month.DECEMBER);

        private final Month month;

        MONTHS(Month month) {
            this.month = month;
        }

        public Month getMonth() {
            return month;
        }
    }

    public static final String PERIOD_START_DATE = "2020-01-01";
    public static final String BASE_URL = "https://data.binance.vision/";
    public static final LocalDate START_DATE = LocalDate.of(2017, 1, 1);
    public static final LocalDate END_DATE = LocalDate.now();

    public static List<YEARS> getYears() {
        return Arrays.asList(YEARS.values());
    }

    public static List<INTERVALS> getIntervals() {
        return Arrays.asList(INTERVALS.values());
    }

    public static List<TRADING_TYPE> getTradingTypes() {
        return Arrays.asList(TRADING_TYPE.values());
    }

    public static List<MONTHS> getMonths() {
        return Arrays.asList(MONTHS.values());
    }
}
