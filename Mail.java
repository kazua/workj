import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
 
public class Mail {
    private String mailP = "[\\w\\.\\-]+@(?:[\\w\\-]+\\.)+[\\w\\-]+";
 
    /**
     * メールを送信する
     *
     * @param fad
     *            送信元アドレス
     * @param fan
     *            送信元名
     * @param tad
     *            送信先アドレス
     * @param tan
     *            送信先名
     * @param tle
     *            タイトル
     * @param mes
     *            本文
     * @param atd
     *            添付ファイルパス
     *
     * @author kazua
     */
    public String sendMailProc(String fad, String fan, String tad, String tan,
            String tle, String mes, String atd) {
        try {
            // プロパティ作成
            Properties ppt = System.getProperties();
 
            // メールパターンマッチ
            Pattern ptn = Pattern.compile(mailP);
            Matcher mc = ptn.matcher(fad);
            if (!mc.matches()) {
                return "送信元メールアドレスが異常です：" + fad;
            }
            mc = ptn.matcher(tad);
            if (!mc.matches()) {
                return "送信先メールアドレスが異常です：" + tad;
            }
 
            // パラメータチェック
            if ((tle == null || tle.equals(""))
                    && (mes == null || mes.equals(""))
                    && (atd == null || atd.equals(""))) {
                return "タイトル、本文、添付ファイルが全て未指定です";
            }
 
            // SMTPアドレス設定
            ppt.put("mail.smtp.host", "***.***.***.***");
 
            // メールセッション作成
            Session ssm = Session.getDefaultInstance(ppt, null);
            MimeMessage mms = new MimeMessage(ssm);
 
            // 送信元設定
            mms.setFrom(new InternetAddress(fad, MimeUtility.encodeWord(fan,
                    "UTF-8", "B")));
 
            // 送信先設定
            InternetAddress adr = new InternetAddress(tad,
                    MimeUtility.encodeWord(tan, "UTF-8", "B"));
            mms.setRecipient(Message.RecipientType.TO, adr);
 
            // メール形式設定
            mms.setHeader("Content-Type", "text/plain");
 
            // 題名設定
            if (tle != null && !tle.equals("")) {
                mms.setSubject(MimeUtility.encodeText(tle, "UTF-8", "B"));
            }
 
            // メール本体作成
            MimeMultipart mmp = new MimeMultipart();
 
            // 本文設定
            if (mes != null && !mes.equals("")) {
                MimeBodyPart tpt = new MimeBodyPart();
                tpt.setContent(mes, "text/plain; charset=\"UTF-8\"");
                mmp.addBodyPart(tpt);
            }
 
            // 添付ファイル設定
            if (atd != null && !atd.equals("")) {
                MimeBodyPart mbp = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(atd);
                DataHandler dhr = new DataHandler(fds);
                mbp.setDataHandler(dhr);
                mbp.setFileName(MimeUtility.encodeText(fds.getName(),
                        "iso-2022-jp", "B"));
                mmp.addBodyPart(mbp);
            }
 
            mms.setContent(mmp);
 
            // メール送信
            Transport.send(mms);
 
            return "メール送信完了";
        } catch (Exception e) {
            return "メール送信失敗：" + e.toString();
        }
    }
}
 
class sendMail {
    public static void main(String[] args) {
        try {
            Mail ml = new Mail();
            String rtn = ml.sendMailProc("***@***.***", "テスト川テスト男",
                    "***@***.***", "テスト山テスト子", "aa", "cc",
                    "E:/github/workj/Mail.java");
            System.out.println(rtn);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}