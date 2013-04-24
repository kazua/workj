import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class zipCreate {
	public static void zipFileMake(String dir, String zipfile) throws Exception {
		ZipOutputStream zo = new ZipOutputStream(new FileOutputStream(zipfile));
		zo.setEncoding("MS932");
		File tgtdir = new File(dir);
		File[] tgtpaths = tgtdir.listFiles();
		for (int i = 0; i < tgtpaths.length; i++) {
			zipFileMakeProc(zo, tgtpaths[i],"");
		}
		zo.flush();
		zo.close();
	}
	private static void zipFileMakeProc(ZipOutputStream zo, File path, String hrc)
			throws Exception {
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				zipFileMakeProc(zo, files[i], hrc + path.getName() + "/");
			}
		} else {
			ZipEntry entry = new ZipEntry(hrc + path.getName().replace("\\", "/"));
			zo.putNextEntry(entry);
			byte buf[] = new byte[1024];
			int size;
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(path.getPath()));
			while ((size = in.read(buf, 0, 1024)) != -1) {
				zo.write(buf, 0, size);
			}
			zo.closeEntry();
			in.close();
		}
	}
	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date now = new Date();
			zipFileMake("D:/写真", "E:/backup/家族写真" + sdf.format(now) + ".zip");
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