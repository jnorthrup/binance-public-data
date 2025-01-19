# Binance Public Data Java Port

This directory contains the Java port of the Python scripts for downloading various types of data from Binance. The Java classes use practical NIO calls and enums for efficient data handling.

## Installing the dependencies

To compile and run the Java classes, you need to have Java Development Kit (JDK) installed on your system. You can download it from [here](https://www.oracle.com/java/technologies/javase-downloads.html).

## Compiling the Java classes

Navigate to the `java/src/main/java` directory and run the following command to compile the Java classes:

```sh
javac com/binance/**/*.java
```

## Running the Java classes

To run the Java classes, use the `java` command followed by the fully qualified class name and the required arguments.

### Download klines

```sh
java com.binance.download.DownloadKline -t <market_type>
```

Running this command will download all available monthly and daily **spot**, **USD-M Futures** or **COIN-M Futures** kline data for all symbols and intervals from **2020-01-01**.

#### Running with arguments

These are the available arguments that can be used when running `DownloadKline`:

| Argument        | Explanation | Default | Mandatory |      
| :---------------: | ---------------- | :----------------: | :----------------: |
| -t              | Market type: **spot**, **um** (USD-M Futures), **cm** (COIN-M Futures) | spot | Yes |
| -s              | Single **symbol** or multiple **symbols** separated by space | All symbols | No |
| -i              | single kline **interval** or multiple **intervals** separated by space      | All intervals | No |
| -y              | Single **year** or multiple **years** separated by space| All available years from 2020 to current year | No |
| -m              | Single **month** or multiple **months** separated by space | All available months | No |
| -d              | single **date** or multiple **dates** separated by space    | All available dates from 2020-01-01 | No |
| -startDate      | **Starting date** to download in [YYYY-MM-DD] format    | 2020-01-01 | No |
| -endDate        | **Ending date** to download in [YYYY-MM-DD] format     | Current date | No |
| -skip-monthly   | 1 to skip downloading of monthly data | 0 | No |
| -skip-daily     | 1 to skip downloading of daily data | 0 | No |
| -folder         | **Directory** to store the downloaded data    | Current directory | No |
| -c              | 1 to download **checksum file** | 0 | No |
| -h              | show help messages| - | No |

#### Example

e.g download ETHUSDT BTCUSDT BNBBUSD spot kline of 1 week interval from year 2020, month of Feb and Dec with CHECKSUM file:

```sh
java com.binance.download.DownloadKline -t spot -s ETHUSDT BTCUSDT BNBBUSD -i 1w -y 2020 -m 02 12 -c 1
```

e.g download all symbols' daily USD-M futures kline of 1 minute interval from 2021-01-01 to 2021-02-02:

```sh
java com.binance.download.DownloadKline -t um -i 1m -skip-monthly 1 -startDate 2021-01-01 -endDate 2021-02-02
```

### Download trades

```sh
java com.binance.download.DownloadTrade -t <market_type>
```

Running this command will download all available monthly and daily **spot**, **USD-M Futures** or **COIN-M Futures** trade data for all symbols from **2020-01-01**.

#### Running with arguments

These are the available arguments that can be used when running `DownloadTrade`:

| Argument        | Explanation | Default | Mandatory |       
| :---------------: | ---------------- | :----------------: | :----------------: |
| -t              | Market type: **spot**, **um** (USD-M Futures), **cm** (COIN-M Futures) | spot | Yes |
| -s              | Single **symbol** or multiple **symbols** separated by space | All symbols | No |
| -y              | Single **year** or multiple **years** separated by space| All available years from 2020 to current year | No |
| -m              | Single **month** or multiple **months** separated by space | All available months | No |
| -d              | single **date** or multiple **dates** separated by space    | All available dates from 2020-01-01 | No |
| -startDate      | **Starting date** to download in [YYYY-MM-DD] format    | 2020-01-01 | No |
| -endDate        | **Ending date** to download in [YYYY-MM-DD] format     | Current date | No |
| -skip-monthly   | 1 to skip downloading of monthly data | 0 | No |
| -skip-daily     | 1 to skip downloading of daily data | 0 | No |
| -folder         | **Directory** to store the downloaded data    | Current directory | No |
| -c              | 1 to download **checksum file** | 0 | No |
| -h              | show help messages| - | No |

#### Example

e.g download ETHUSDT BTCUSDT BNBBUSD spot trades from year 2020, month of Feb and Dec with CHECKSUM file:

```sh
java com.binance.download.DownloadTrade -t spot -s ETHUSDT BTCUSDT BNBBUSD -y 2020 -m 02 12 -c 1
```

e.g download all symbols' daily USD-M futures trades from 2021-01-01 to 2021-02-02:

```sh
java com.binance.download.DownloadTrade -t um -skip-monthly 1 -startDate 2021-01-01 -endDate 2021-02-02
```

### Download aggTrades

```sh
java com.binance.download.DownloadAggTrade -t <market_type>
```

Running this command will download all available monthly and daily **spot**, **USD-M Futures** or **COIN-M Futures** aggregated trades data for all symbols from **2020-01-01**.

#### Running with arguments

These are the available arguments that can be used when running `DownloadAggTrade`:

| Argument        | Explanation | Default | Mandatory |       
| :---------------: | ---------------- | :----------------: | :----------------: |
| -t              | Market type: **spot**, **um** (USD-M Futures), **cm** (COIN-M Futures) | spot | Yes |
| -s              | Single **symbol** or multiple **symbols** separated by space | All symbols | No |
| -y              | Single **year** or multiple **years** separated by space| All available years from 2020 to current year | No |
| -m              | Single **month** or multiple **months** separated by space | All available months | No |
| -d              | single **date** or multiple **dates** separated by space    | All available dates from 2020-01-01 | No |
| -startDate      | **Starting date** to download in [YYYY-MM-DD] format    | 2020-01-01 | No |
| -endDate        | **Ending date** to download in [YYYY-MM-DD] format     | Current date | No |
| -skip-monthly   | 1 to skip downloading of monthly data | 0 | No |
| -skip-daily     | 1 to skip downloading of daily data | 0 | No |
| -folder         | **Directory** to store the downloaded data    | Current directory | No |
| -c              | 1 to download **checksum file** | 0 | No |
| -h              | show help messages| - | No |

#### Example

e.g download ETHUSDT BTCUSDT BNBBUSD spot aggTrades from year 2020, month of Feb and Dec with CHECKSUM file:

```sh
java com.binance.download.DownloadAggTrade -t spot -s ETHUSDT BTCUSDT BNBBUSD -y 2020 -m 02 12 -c 1
```

e.g download all symbols' daily USD-M futures aggTrades from 2021-01-01 to 2021-02-02:

```sh
java com.binance.download.DownloadAggTrade -t um -skip-monthly 1 -startDate 2021-01-01 -endDate 2021-02-02
```

### Futures-Only Data

The following Java classes are only used for futures klines data. Running these commands will download all available monthly and daily **USD-M Futures** or **COIN-M Futures** indexPriceKlines, markPriceKlines, or premiumPriceKlines for all symbols from **2020-01-01**.

#### Download Futures Index Price Klines

```sh
java com.binance.download.DownloadFuturesIndexPriceKlines -t <market_type>
```

#### Download Futures Mark Price Klines

```sh
java com.binance.download.DownloadFuturesMarkPriceKlines -t <market_type>
```

#### Download Futures Premium Index Klines

```sh
java com.binance.download.DownloadFuturesPremiumIndexKlines -t <market_type>
```

#### Running with arguments

These are the available arguments that can be used when running the scripts:

| Argument        | Explanation | Default | Mandatory |      
| :---------------: | ---------------- | :----------------: | :----------------: |
| -t              | Market type: **um** (USD-M Futures), **cm** (COIN-M Futures)| - | Yes |
| -s              | Single **symbol** or multiple **symbols** separated by space | All symbols | No |
| -i              | single kline **interval** or multiple **intervals** separated by space      | All intervals | No |
| -y              | Single **year** or multiple **years** separated by space| All available years from 2020 to current year | No |
| -m              | Single **month** or multiple **months** separated by space | All available months | No |
| -d              | single **date** or multiple **dates** separated by space    | All available dates from 2020-01-01 | No |
| -startDate      | **Starting date** to download in [YYYY-MM-DD] format    | 2020-01-01 | No |
| -endDate        | **Ending date** to download in [YYYY-MM-DD] format     | Current date | No |
| -skip-monthly   | 1 to skip downloading of monthly data | 0 | No |
| -skip-daily     | 1 to skip downloading of daily data | 0 | No |
| -folder         | **Directory** to store the downloaded data    | Current directory | No |
| -c              | 1 to download **checksum file** | 0 | No |
| -h              | show help messages| - | No |

#### Example

e.g download Futures BTCUSDT USD-M indexPriceKlines:

```sh
java com.binance.download.DownloadFuturesIndexPriceKlines -t um -s BTCUSDT
```

e.g download ETHUSDT BTCUSDT BNBUSDT USD-M markPriceKlines of 1 week from year 2020, month of Feb and Dec with CHECKSUM file:

```sh
java com.binance.download.DownloadFuturesMarkPriceKlines -t um -s ETHUSDT BTCUSDT BNBUSDT -i 1w -y 2020 -m 02 12 -c 1
```

e.g download all symbols' daily COIN-M premiumPriceKlines of 1 minute interval from 2021-01-01 to 2021-02-02:

```sh
java com.binance.download.DownloadFuturesPremiumIndexKlines -t cm -skip-monthly 1 -i 1m -startDate 2021-01-01 -endDate 2021-02-02
```
