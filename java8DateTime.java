import java.time.*;
 
//write kazua Java8日付API試し
public class java8DateTime{
	public static void main(String[] args){
		LocalDate ld = LocalDate.now();//現在日付取得
		LocalDate ld2 = LocalDate.of(2014,5,4);//2014年5月4日のデータ取得
		System.out.println(ld.toString());//日付文字列
		System.out.println(ld2.toString());//日付文字列
		System.out.println(ld.getDayOfMonth());//月中の日数
		System.out.println(ld.isAfter(ld2));//引数の日付データが過去かどうか
		System.out.println(ld.isBefore(ld2));//引数の日付データが未来かどうか
		System.out.println(ld.lengthOfMonth());//その月の日数
		System.out.println(ld.plusYears(2).plusMonths(3).plusDays(11).toString());//各年、月、日単位で加算
		System.out.println(ld.minusYears(3).minusMonths(4).minusDays(20).toString());//各年、月、日単位で減算

		LocalTime lt = LocalTime.now();//現在時刻取得
		LocalTime lt2 = LocalTime.of(11,23,54);//11時23分54秒取得
		System.out.println(lt.toString());//時刻文字列
		System.out.println(lt2.toString());//時刻文字列
		System.out.println(lt.getHour());//時
		System.out.println(lt.getMinute());//分
		System.out.println(lt.getSecond());//秒
		System.out.println(lt.getNano());//ナノ秒
		System.out.println(lt.isAfter(lt2));//引数の時刻が過去かどうか
		System.out.println(lt.isBefore(lt2));//引数の時刻が未来かどうか
		System.out.println(lt.plusHours(2).plusMinutes(16).plusSeconds(34).plusNanos(46500).toString());//各時、分、秒、ナノ秒単位で加算
		System.out.println(lt.minusHours(4).minusMinutes(34).minusSeconds(43).minusNanos(42199).toString());
		System.out.println(lt.toSecondOfDay());//その日の経過秒数

		LocalDateTime ldt = LocalDateTime.now();//現在日時取得
		LocalDateTime ldt2 = LocalDateTime.of(2014,3,30,21,14,36,45600);//2014年3月30日21時14分36秒45600ナノ秒
		System.out.println(ldt.toString());//日時
		System.out.println(ldt2.toString());//日時
		System.out.println(ldt.getDayOfMonth());//月中の日数
		System.out.println(ldt.getDayOfYear());//年中の日数
		System.out.println(ldt.getHour());//時
		System.out.println(ldt.getMinute());//分

		ZonedDateTime zdt = ZonedDateTime.now();//現在地域日時取得
		ZonedDateTime zdt2 = ZonedDateTime.of(2014,3,23,19,17,55,3456000,ZoneId.of("Asia/Tokyo"));//日本2014年3月23日19時17分55秒3456000ナノ秒
		ZonedDateTime zdt3 = ZonedDateTime.of(ldt2,ZoneId.of("Asia/Tokyo"));//日本2014年3月30日21時14分36秒456000ナノ秒
		ZonedDateTime zdt4 = ZonedDateTime.of(ld2,lt2,ZoneId.of("Asia/Tokyo"));//日本2014年5月4日11時23分54秒
		System.out.println(zdt.toString());//地域日時
		System.out.println(zdt2.toString());//地域日時
		System.out.println(zdt3.toString());//地域日時
		System.out.println(zdt4.toString());//地域日時
		System.out.println(zdt.toLocalDate().toString());//LocalDateに変換
		System.out.println(zdt.toLocalDateTime().toString());//LocalDateTimeに変換
	}
}