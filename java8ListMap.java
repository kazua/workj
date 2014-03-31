import java.util.*;

public class java8ListMap{
	public static void main(String[] args){
		List<String> l = new ArrayList<String>(Arrays.asList("a","b","c"));
		l.forEach(s -> System.out.println(s));//Listの要素に対してラムダ式で処理を実施
		l.sort((o1,o2) -> o2.compareTo(o1));//Listの要素をラムダ式の条件でソート
		l.forEach(s -> System.out.println(s));
		l.replaceAll(s -> s.toUpperCase());//Listの要素をラムダ式で更新
		l.forEach(s -> System.out.println(s));
		l.removeIf(s -> s.startsWith("B"));//Listの要素をラムダ式の条件で削除
		l.forEach(s -> System.out.println(s));
		l.stream().map(s -> s + "z").forEach(s -> System.out.println(s));//ListをStreamに変換

		Map<String,String> m = new HashMap<>();
		m.put("a","test1");
		m.put("b","test2");
		m.put("c","test3");
		m.forEach((k,v) -> System.out.println(k + "-" + v));//Mapの要素に対してラムダ式で処理を実施
		System.out.println(m.getOrDefault("c","10"));//指定したキーがあればその値、なければ第二引数のデフォルト値
		System.out.println(m.getOrDefault("d","10"));
		m.putIfAbsent("d","test4");//指定したキーがなければMapに値追加
		m.forEach((k,v) -> System.out.println(k + "-" + v));
		m.replace("a","test5");//指定したキーがあればMapの値を更新
		m.forEach((k,v) -> System.out.println(k + "-" + v));
		m.replaceAll((k,v) -> "test" + (Integer.valueOf(v.substring(v.length()-1))+1));//Mapの要素をラムダ式で更新
		m.forEach((k,v) -> System.out.println(k + "-" + v));
		m.computeIfAbsent("a",(k) -> "test10");//指定したキーが存在しない場合、ラムダ式の戻り値をMapに追加
		m.computeIfAbsent("e",(k) -> "test10");
		m.forEach((k,v) -> System.out.println(k + "-" + v));
		m.computeIfPresent("f",(k,v) -> "test11");//指定したキーが存在する場合、ラムダ式の戻り値でMapの値を更新
		m.computeIfPresent("d",(k,v) -> "test11");
		m.forEach((k,v) -> System.out.println(k + "-" + v));
		m.remove("c","test3");//キーと値が合致する要素があれば削除
		m.forEach((k,v) -> System.out.println(k + "-" + v));
		m.remove("c","test4");
		m.forEach((k,v) -> System.out.println(k + "-" + v));
	}
}
