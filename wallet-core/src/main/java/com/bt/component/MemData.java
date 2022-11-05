package com.bt.component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class MemData {
    private static Map<String,BigDecimal> EXCHANGE_RATE_MAP = new HashMap<>();
    private static Map<String,BigDecimal> COIN_PRICE_USD_MAP = new HashMap<>();

//    private static Map<String, BigDecimal> estimatesmartfeeMap = new HashMap<>();
//    private static Map<String, BigInteger> gasLimitMap = new HashMap<>();
//    private static Map<String, BigInteger> gasPriceMap = new HashMap<>();
//    private static Map<String, Integer> bheightMap = new HashMap<>();

    private static Map<String, String> coinSeriesMap = new HashMap<>();


    public static BigDecimal addExchangeRate(String code,BigDecimal rate){
        return EXCHANGE_RATE_MAP.put(code, rate);
    }

    public static BigDecimal getExchangeRate(String code){
        return EXCHANGE_RATE_MAP.get(code);
    }

    public static BigDecimal addCoinPriceUsd(String symbol,BigDecimal price){
        return COIN_PRICE_USD_MAP.put(symbol, price);
    }

    public static BigDecimal getCoinPriceUsd(String symbol){
        return COIN_PRICE_USD_MAP.get(symbol)==null?BigDecimal.ZERO:COIN_PRICE_USD_MAP.get(symbol);
    }

    public static Map<String, BigDecimal> getExchangeRateMap() {
        return EXCHANGE_RATE_MAP;
    }

    public static Map<String, BigDecimal> getCoinPriceUsdMap() {
        return COIN_PRICE_USD_MAP;
    }

//    public static BigDecimal getEstimatesmartfee(String symbal) {
//        return estimatesmartfeeMap.get(symbal);
//    }
//
//    public static void updateEstimatesmartfee(String symbal, BigDecimal fee) {
//        estimatesmartfeeMap.put(symbal, fee);
//    }

//    public static BigInteger getGasLimitMap(String symbal) {
//        return gasLimitMap.get(symbal);
//    }
//
//    public static void updateGasLimitMap(String symbal, BigInteger fee) {
//        gasLimitMap.put(symbal, fee);
//    }
//
//    public static BigInteger getGasPriceMap(String symbal) {
//        return gasPriceMap.get(symbal);
//    }
//
//    public static void updateGasPriceMap(String symbal, BigInteger fee) {
//        gasPriceMap.put(symbal, fee);
//    }


//    public static Integer getBheight(String symbal) {
//        return bheightMap.get(symbal);
//    }
//
//    public static void updateBheight(String symbal, Integer height) {
//        bheightMap.put(symbal, height);
//    }

    public static String getCoinSeriesBySymbol(String symbol){
        return coinSeriesMap.get(symbol);
    }

    public static void updateCoinSeriesBySymbol(String symbol,String coinSeries){
        coinSeriesMap.put(symbol,coinSeries);
    }

}
