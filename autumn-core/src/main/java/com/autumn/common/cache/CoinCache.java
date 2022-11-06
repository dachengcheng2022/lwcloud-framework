package com.autumn.common.cache;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.autumn.mapper.wallet.TwalletMapper;
import com.autumn.wallet.domain.coin.Tcoininfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class CoinCache {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Map<Integer, Tcoininfo> coinIdMap = new HashMap<Integer, Tcoininfo>();

	private Map<String, Tcoininfo> coinSymbolMap = new HashMap<String, Tcoininfo>();
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	@Resource
	private TwalletMapper walletMapper;

	private ScheduledExecutorService executors = Executors.newSingleThreadScheduledExecutor();

	@PostConstruct
	public void init() {
		updateCoinMap();
		executors.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {
					updateCoinMap();
				} catch (Throwable e) {
					logger.error("updateCoinMap error!",e);
				}
			}
			
		}, 5, 5, TimeUnit.MINUTES); //1分钟同步一次数据
	}

	@PreDestroy
	public void shutdown() {
		executors.shutdown();
	}
	
	private void updateCoinMap() {
		List<Tcoininfo> coins = walletMapper.selectList(Wrappers.<Tcoininfo>lambdaQuery().eq(Tcoininfo::getCstatus,1));
		lock.writeLock().lock();
		try {
			coinIdMap.clear();
			coinSymbolMap.clear();
			for (Tcoininfo coin : coins) {
				coinIdMap.put(coin.getCoinid(), coin);
				coinSymbolMap.put(coin.getCsymbol(), coin);
			}
			logger.info("updateCoinMap ~~");
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public Tcoininfo getCoininfo(String symbol) {
		lock.readLock().lock();
		try {
			return coinSymbolMap.get(symbol);
		} finally {
			lock.readLock().unlock();
		}
	}

	public Tcoininfo getCoininfo(Integer conid) {
		lock.readLock().lock();
		try {
			return coinIdMap.get(conid);
		} finally {
			lock.readLock().unlock();
		}
	}

	public List<Tcoininfo> getCoininfos() {
		lock.readLock().lock();
		try {
			return new ArrayList<Tcoininfo>(coinSymbolMap.values());
		} finally {
			lock.readLock().unlock();
		}
	}

}
