//write kazua

import java.io.File;
import java.util.*;

public class fileSearch {
	private List<String> getFiles(String path) {
		List<String> pl = new ArrayList<String>();
		File file = new File(path);
		if (!file.isDirectory()) file = file.getParentFile();
		for (File fc : file.listFiles()) {
			if (fc.isDirectory()) pl.addAll(getFiles(fc.getPath()));
			else pl.add(fc.getPath());
		}
		return pl;
	}
	public static void main(String[] args) {
		try {
			List<String> rl = new ArrayList<String>();
			fileSearch fs = new fileSearch();
			rl = fs.getFiles("D:/写真");
			for (int i = 0; i < rl.size(); i++) System.out.println(rl.get(i));
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