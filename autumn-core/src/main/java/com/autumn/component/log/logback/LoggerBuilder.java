package com.autumn.component.log.logback;

import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;

import org.slf4j.LoggerFactory;

public class LoggerBuilder {

	private static final Map<String, Logger> container = new HashMap<String, Logger>();

	private static final String COIN_LOG_HOME = "${COIN_LOG_HOME}";
	private static final String COIN_LOG_PATTERN = "${COIN_LOG_PATTERN}";
	private static final String COIN_MAX_LOG_SIZE = "${COIN_MAX_LOG_SIZE}";
	private static final String COIN_MAX_HISTORY = "${COIN_MAX_HISTORY}";
	private static final String COIN_TOTAL_SIZE_CAP = "${COIN_TOTAL_SIZE_CAP}";
	
	/**默认日志文件路径*/
	private static final String DEFAULT_LOG_HOME = "./logs/coin";

	/**默认日志格式*/
	private static final String DEFAULT_LOG_PATTERN = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %C{40} - %msg%n";

	/**默认单个日志文件大小*/
	private static final String DEFAULT_LOG_SIZE = "50MB";

	/**默认日志保存时长(天)*/
	private static final String DEFAULT_LOG_HISTORY = "30";
	
	/**默认总日志文件大小*/
	private static final String DEFAULT_LOG_CAP = "10GB";
	
	public static Logger getLogger(String name) {
		Logger logger = container.get(name);
		if (logger != null) {
			return logger;
		}
		synchronized (LoggerBuilder.class) {
			logger = container.get(name);
			if (logger != null) {
				return logger;
			}
			logger = build(name);
			container.put(name, logger);
		}
		return logger;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Logger build(String coin) {
		LoggerContext context = (LoggerContext) LoggerFactory
				.getILoggerFactory();
		Logger logger = context.getLogger("COIN_"+coin);
		logger.setAdditive(false);
		RollingFileAppender appender = new RollingFileAppender();
		appender.setContext(context);
		appender.setName("COIN_"+coin);
		appender.setAppend(true);
		appender.setPrudent(false);
		SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
		String fp = getLogHome(coin, context);

		policy.setMaxFileSize(FileSize.valueOf(getMaxFileSize(context)));
		policy.setFileNamePattern(fp);
		policy.setMaxHistory(getMaxHistory(context));
		policy.setTotalSizeCap(FileSize.valueOf(getTotalSizeCap(context)));
		policy.setParent(appender);
		policy.setContext(context);
		policy.start();

		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(context);
		encoder.setPattern(getLogPattern(context));
		encoder.start();

		appender.setRollingPolicy(policy);
		appender.setEncoder(encoder);
		appender.start();
		logger.addAppender(appender);

		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setContext(context);
		consoleAppender.setName("COIN_"+coin);
		consoleAppender.setEncoder(encoder);
		consoleAppender.start();
		logger.addAppender(consoleAppender);
		return logger;
	}
	
	private static String getLogHome(String coin, LoggerContext context) {
		String fileSuffix = "/" +coin + ".%d{yyyy-MM-dd}.%i.log";
		String logHome = OptionHelper.substVars(COIN_LOG_HOME + fileSuffix, context);
		if (logHome == null || logHome.indexOf("UNDEFINED") != -1) {
			logHome = OptionHelper.substVars(DEFAULT_LOG_HOME + fileSuffix, context);
		}
		return logHome;
	}
	
	private static String getLogPattern(LoggerContext context) {
		String logPattern = OptionHelper.substVars(COIN_LOG_PATTERN, context);
		if (logPattern == null || logPattern.indexOf("UNDEFINED") != -1) {
			logPattern = OptionHelper.substVars(DEFAULT_LOG_PATTERN, context);;
		}
		return logPattern;
	}
	
	private static String getMaxFileSize(LoggerContext context) {
		String maxFileSize = OptionHelper.substVars(COIN_MAX_LOG_SIZE, context);
		if (maxFileSize == null || maxFileSize.indexOf("UNDEFINED") != -1) {
			maxFileSize = DEFAULT_LOG_SIZE;
		}
		return maxFileSize;
	}
	
	private static int getMaxHistory(LoggerContext context) {
		String maxHistory = OptionHelper.substVars(COIN_MAX_HISTORY, context);
		if (maxHistory == null || maxHistory.indexOf("UNDEFINED") != -1) {
			maxHistory = DEFAULT_LOG_HISTORY;
		}
		return Integer.valueOf(maxHistory);
	}
	
	private static String getTotalSizeCap(LoggerContext context) {
		String totalSizeCap = OptionHelper.substVars(COIN_TOTAL_SIZE_CAP, context);
		if (totalSizeCap == null || totalSizeCap.indexOf("UNDEFINED") != -1) {
			totalSizeCap = DEFAULT_LOG_CAP;
		}
		return totalSizeCap;
	}
}