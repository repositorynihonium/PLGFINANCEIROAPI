package br.com.plataformalancamento.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtility {
	
	public static final String FORMATO_YYYYMMDD = "yyyyMMdd";
	
	public static String recuperarDataAtual(String formato) {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formato);
		return localDateTime.format(dateTimeFormatter);
	}
	
	public static String formatarData(LocalDateTime localDateTime, String formatoData) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatoData);
		return localDateTime.format(dateTimeFormatter);
	}
	
}