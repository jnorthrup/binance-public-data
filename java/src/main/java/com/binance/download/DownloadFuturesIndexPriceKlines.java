package com.binance.download;

import com.binance.enums.Enums;
import com.binance.utility.Utility;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DownloadFuturesIndexPriceKlines {

    public static void downloadMonthlyIndexPriceKlines(Enums.TRADING_TYPE tradingType, List<String> symbols, int numSymbols, List<Enums.INTERVALS> intervals, List<Enums.YEARS> years, List<Enums.MONTHS> months, String startDate, String endDate, String folder, boolean checksum) throws IOException {
        int current = 0;
        String dateRange = null;

        if (startDate != null && endDate != null) {
            dateRange = startDate + " " + endDate;
        }

        LocalDate start = startDate != null ? Utility.convertToDateObject(startDate) : Enums.START_DATE;
        LocalDate end = endDate != null ? Utility.convertToDateObject(endDate) : Enums.END_DATE;

        System.out.println("Found " + numSymbols + " symbols");

        for (String symbol : symbols) {
            System.out.println("[" + (current + 1) + "/" + numSymbols + "] - start download monthly " + symbol + " indexPriceKlines ");
            for (Enums.INTERVALS interval : intervals) {
                for (Enums.YEARS year : years) {
                    for (Enums.MONTHS month : months) {
                        LocalDate currentDate = LocalDate.of(year.ordinal() + 2017, month.getMonth(), 1);
                        if (!currentDate.isBefore(start) && !currentDate.isAfter(end)) {
                            String path = Utility.getPath(tradingType, "indexPriceKlines", "monthly", symbol);
                            String fileName = symbol.toUpperCase() + "-" + interval + "-" + year + "-" + String.format("%02d", month.ordinal() + 1) + ".zip";
                            Utility.downloadFile(path, fileName, dateRange, folder);

                            if (checksum) {
                                String checksumPath = Utility.getPath(tradingType, "indexPriceKlines", "monthly", symbol);
                                String checksumFileName = symbol.toUpperCase() + "-" + interval + "-" + year + "-" + String.format("%02d", month.ordinal() + 1) + ".zip.CHECKSUM";
                                Utility.downloadFile(checksumPath, checksumFileName, dateRange, folder);
                            }
                        }
                    }
                }
            }
            current++;
        }
    }

    public static void downloadDailyIndexPriceKlines(Enums.TRADING_TYPE tradingType, List<String> symbols, int numSymbols, List<Enums.INTERVALS> intervals, List<LocalDate> dates, String startDate, String endDate, String folder, boolean checksum) throws IOException {
        int current = 0;
        String dateRange = null;

        if (startDate != null && endDate != null) {
            dateRange = startDate + " " + endDate;
        }

        LocalDate start = startDate != null ? Utility.convertToDateObject(startDate) : Enums.START_DATE;
        LocalDate end = endDate != null ? Utility.convertToDateObject(endDate) : Enums.END_DATE;

        System.out.println("Found " + numSymbols + " symbols");

        for (String symbol : symbols) {
            System.out.println("[" + (current + 1) + "/" + numSymbols + "] - start download daily " + symbol + " indexPriceKlines ");
            for (Enums.INTERVALS interval : intervals) {
                for (LocalDate date : dates) {
                    if (!date.isBefore(start) && !date.isAfter(end)) {
                        String path = Utility.getPath(tradingType, "indexPriceKlines", "daily", symbol);
                        String fileName = symbol.toUpperCase() + "-" + interval + "-" + date + ".zip";
                        Utility.downloadFile(path, fileName, dateRange, folder);

                        if (checksum) {
                            String checksumPath = Utility.getPath(tradingType, "indexPriceKlines", "daily", symbol);
                            String checksumFileName = symbol.toUpperCase() + "-" + interval + "-" + date + ".zip.CHECKSUM";
                            Utility.downloadFile(checksumPath, checksumFileName, dateRange, folder);
                        }
                    }
                }
            }
            current++;
        }
    }

    public static void main(String[] args) throws IOException {
        // Parse arguments and initiate downloads
        // This part of the code will be implemented later
    }
}
