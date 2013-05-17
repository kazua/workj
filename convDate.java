//write kazua

import java.util.*;
import java.text.*;

public class convDate {
	private String convJtGDate(String src) throws ParseException {
		Integer gc = 0;
		String gg = src.substring(0, 2);
		Integer yy = Integer.parseInt(src.substring(2, src.indexOf("年")));
		Integer mm = Integer.parseInt(src.substring(src.indexOf("年") + 1,
				src.indexOf("月")));
		Integer dd = Integer.parseInt(src.substring(src.indexOf("月") + 1,
				src.indexOf("日")));

		if (gg.equals("平成")) {
			gc = 4;
		} else if (gg.equals("昭和")) {
			gc = 3;
		} else if (gg.equals("大正")) {
			gc = 2;
		} else if (gg.equals("明治")) {
			gc = 1;
		}
		Locale.setDefault(new Locale("ja", "JP", "JP"));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.ERA, gc);
		cal.set(yy, mm - 1, dd);
		Locale.setDefault(new Locale("en", "EN", "EN"));
		SimpleDateFormat gdf = new SimpleDateFormat("yyyy/MM/dd");

		return gdf.format(cal.getTime());
	}
	private String convGtJDate(String src) throws ParseException {
		Locale.setDefault(new Locale("en", "EN", "EN"));
		SimpleDateFormat gdf = new SimpleDateFormat("yyyyMMdd");
		Locale.setDefault(new Locale("ja", "JP", "JP"));
		SimpleDateFormat jdf = new SimpleDateFormat("GGGGyy年MM月dd日");
		return jdf.format(gdf.parse(src.replace("/", "")));
	}
	public static void main(String[] args) {
		try {
			convDate cd = new convDate();
			System.out.println(cd.convJtGDate("平成25年4月1日"));
			System.out.println(cd.convJtGDate("昭和56年11月2日"));
			System.out.println(cd.convJtGDate("昭和43年03月21日"));
			System.out.println(cd.convJtGDate("大正3年9月2日"));
			System.out.println(cd.convJtGDate("明治15年8月13日"));

			System.out.println(cd.convGtJDate("2013/04/01"));
			System.out.println(cd.convGtJDate("1981/11/02"));
			System.out.println(cd.convGtJDate("1968/03/21"));
			System.out.println(cd.convGtJDate("1914/09/02"));
			System.out.println(cd.convGtJDate("1882/08/13"));
		} catch (Exception e) {
			System.out.println(e.toString());
			if (e.getCause() != null)
				for (StackTraceElement ste : e.getCause().getStackTrace())
					System.out.println(ste.toString());
			else
				for (StackTraceElement ste : e.getStackTrace())
					System.out.println(ste.toString());
		}
	}
}