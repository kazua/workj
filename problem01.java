public class problem01 {
	public static int get3and5Sum(int last) {
		int intSum = 0;
		for (int i = 0; i < last; i++) if (i % 3 == 0 || i % 5 == 0) intSum += i;
		return intSum;
	}
	public static void main(String[] args) {
		System.out.println(get3and5Sum(1000));
	}
}