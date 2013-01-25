//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%202
//K.A

public class problem02 {
	public static int fbn(int num) {
		int bef1 = 0;
		int bef2 = 1;
		for (int i = 0; i < num; i++) {
			int backbef1 = bef1;
			bef1 = bef2;
			bef2 = backbef1 + bef2;
		}
		return bef1;
	}

	public static int getFbnSum(int last) {
		int intSum = 0;
		for (int i = 1; fbn(i) <= last; i++) if (fbn(i) % 2 == 0) intSum += fbn(i);
		return intSum;
	}

	public static void main(String[] args) {
		System.out.println(getFbnSum(4000000));
	}
}