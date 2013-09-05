import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.activation.FileDataSource;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.codec.binary.Base64;

public class MailC {
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
            // メールアドレスパターンマッチ
            Pattern ptn = Pattern.compile(mailP);
            Matcher mc = ptn.matcher(fad);
            if (!mc.matches()) return "送信元メールアドレスが異常です：" + fad;
            mc = ptn.matcher(tad);
            if (!mc.matches()) return "送信先メールアドレスが異常です：" + tad;

            // パラメータチェック
            if (fan == null) fan = "";
            if (tan == null) tan = "";
            if ((tle == null || tle.equals(""))
                    && (mes == null || mes.equals(""))
                    && (atd == null || atd.equals(""))) return "タイトル、本文、添付ファイルが全て未指定です";

            // Emailインスタンスの生成
            MultiPartEmail eml = new MultiPartEmail();
            // SMTPアドレス設定
            eml.setHostName("***.***.***.***");
            // 文字コード設定
            eml.setCharset("UTF-8");

            // 送信元設定
            eml.setFrom(fad, fan);
            // 送信先設定
            eml.addTo(tad, tan);
            // 題名設定
            if (tle != null && !tle.equals("")) eml.setSubject(tle);
            // 本文設定
            if (mes != null && !mes.equals("")) eml.setMsg(new String(mes.getBytes("UTF-8")));

            // 添付ファイル設定
            if (atd != null && !atd.equals("")) {
                EmailAttachment eac = new EmailAttachment();
                FileDataSource fds = new FileDataSource(atd);
                eac.setPath(atd);
                eac.setDisposition(EmailAttachment.ATTACHMENT);
                eac.setDescription("=?UTF-8?B?"
                        + new String(Base64.encodeBase64(fds.getName()
                                .getBytes("UTF-8"))) + "?=");
                eac.setName("=?UTF-8?B?"
                        + new String(Base64.encodeBase64(fds.getName()
                                .getBytes("UTF-8"))) + "?=");
                eml.attach(eac);
            }

            // メール送信
            eml.send();

            return "メール送信完了";
        } catch (Exception e) {
            return "メール送信失敗：" + e.toString();
        }
    }
}

class sendMailC {
    public static void main(String[] args) {
        try {
            MailC ml = new MailC();
            String rtn = ml.sendMailProc("***@***.***", "テスト川テスト夫",
                    "***@***.***", "テスト山テスト太", "あいうえお", "あああ",
                    "E:/無題あ.txt");
            System.out.println(rtn);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}