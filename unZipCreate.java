//write kazua

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class unZipCreate {
	private void unZipFileMake(String zpp, String odr) throws Exception {
		ZipFile zfp = new ZipFile(zpp, "MS932");
		String zfn = new File(zpp).getName();
		File opd = new File(odr + "/" + zfn.substring(0, zfn.lastIndexOf('.')));
		if (!opd.exists()) opd.mkdirs();

		Enumeration enm = zfp.getEntries();

		while (enm.hasMoreElements()) {
			ZipEntry ent = (ZipEntry) enm.nextElement();
			InputStream ins = zfp.getInputStream(ent);
			unZipFileMakeProc(ent, opd, ins);
			ins.close();
		}
		zfp.close();
	}
	private void unZipFileMakeProc(ZipEntry ent, File opd, InputStream ins)
			throws Exception {
		if (ent.isDirectory()) {
			new File(opd + "/" + ent.getName()).mkdirs();
		} else {
			File eld = new File(opd + "/" + ent.getName()).getParentFile();
			if (eld != null && !eld.exists()) eld.mkdirs();

			FileOutputStream opf = new FileOutputStream(opd + "/" + ent.getName());
			byte[] buf = new byte[1024];
			int size = 0;
			while ((size = ins.read(buf)) != -1) {
				opf.write(buf, 0, size);
			}
			opf.close();
		}
	}
	public static void main(String[] args) {
		try {
			unZipCreate uzc = new unZipCreate();
			uzc.unZipFileMake("E:/backup/家族写真20130428230436.zip", "E:/共有/output");
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