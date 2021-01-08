package br.com.plataformalancamento.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtility {
	
	public static final String FORMATO_YYYYMMDD = "yyyyMMdd";
	
	public static String recuperarDataAtual(String formato) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formato);
		return localDateTime.format(dateTimeFormatter);
	}
	
	public static String formatarData(Date date, String formatoData) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatoData);
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormatter);
	}
	
}