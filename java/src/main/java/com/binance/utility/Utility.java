package com.binance.utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

    private static final String BASE_URL = "https://data.binance.vision/";

    public static void downloadFile(String basePath, String fileName, String dateRange, String folder) throws IOException {
        String downloadPath = basePath + fileName;
        if (folder != null) {
            basePath = Paths.get(folder, basePath).toString();
        }
        if (dateRange != null) {
            dateRange = dateRange.replace(" ", "_");
            basePath = Paths.get(basePath, dateRange).toString();
        }
        Path savePath = getDestinationDir(Paths.get(basePath, fileName).toString(), folder);

        if (Files.exists(savePath)) {
            System.out.println("\nfile already exists! " + savePath);
            return;
        }

        // make the directory
        if (!Files.exists(Paths.get(basePath))) {
            Files.createDirectories(Paths.get(basePath));
        }

        try {
            URL downloadUrl = new URL(getDownloadUrl(downloadPath));
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            int length = connection.getContentLength();
            int blocksize = Math.max(4096, length / 100);

            try (var inputStream = connection.getInputStream();
                 var outputStream = Files.newOutputStream(savePath)) {
                byte[] buffer = new byte[blocksize];
                int bytesRead;
                int dlProgress = 0;
                System.out.println("\nFile Download: " + savePath);
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    dlProgress += bytesRead;
                    outputStream.write(buffer, 0, bytesRead);
                    int done = (int) (50 * dlProgress / length);
                    System.out.print("\r[" + "#".repeat(done) + ".".repeat(50 - done) + "]");
                }
            }
        } catch (IOException e) {
            System.out.println("\nFile not found: " + getDownloadUrl(downloadPath));
        }
    }

    public static Path getDestinationDir(String fileUrl, String folder) {
        String storeDirectory = System.getenv("STORE_DIRECTORY");
        if (folder != null) {
            storeDirectory = folder;
        }
        if (storeDirectory == null) {
            storeDirectory = Paths.get("").toAbsolutePath().toString();
        }
        return Paths.get(storeDirectory, fileUrl);
    }

    public static String getDownloadUrl(String fileUrl) {
        return BASE_URL + fileUrl;
    }

    public static List<String> getAllSymbols(String type) throws IOException {
        String url;
        switch (type) {
            case "um":
                url = "https://fapi.binance.com/fapi/v1/exchangeInfo";
                break;
            case "cm":
                url = "https://dapi.binance.com/dapi/v1/exchangeInfo";
                break;
            default:
                url = "https://api.binance.com/api/v3/exchangeInfo";
                break;
        }
        try (var inputStream = new URL(url).openStream();
             var reader = new java.io.InputStreamReader(inputStream);
             var jsonReader = javax.json.Json.createReader(reader)) {
            var jsonObject = jsonReader.readObject();
            var symbols = jsonObject.getJsonArray("symbols");
            return symbols.stream()
                    .map(symbol -> symbol.asJsonObject().getString("symbol"))
                    .collect(Collectors.toList());
        }
    }

    public static LocalDate convertToDateObject(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    public static List<LocalDate> getDateRange(LocalDate startDate, LocalDate endDate) {
        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1)
                .collect(Collectors.toList());
    }
}
