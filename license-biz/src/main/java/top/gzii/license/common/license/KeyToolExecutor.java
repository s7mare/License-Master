package top.gzii.license.common.license;

import org.apache.commons.codec.Charsets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *  密钥库文件生成器，需要设置私钥密码和公钥密码
 *  通过创建子进程执行keytool命令，会在项目中生成相关文件
 */

public class KeyToolExecutor {

    //访问私钥库的密码
    private static final String privatePassword="lawosxjaoqXmt";

    //访问公钥库的密码
    private static final String publicPassword="kdpawxAdaEXmt";

    public static void executeCommand(String[] command) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true); // 合并错误流和输出流

        Process process = pb.start();

        // 读取输出
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "GBK"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("命令执行失败，退出码: " + exitCode);
        }
    }

    public static void main(String[] args) throws Exception {
        // 检查keytool是否可用
        try {
            ProcessBuilder pb = new ProcessBuilder("keytool", "-help");
            Process process = pb.start();
            process.waitFor();
        } catch (Exception e) {
            throw new RuntimeException("keytool 命令不可用，请确保JDK已正确安装并配置PATH", e);
        }

        // 1. 生成密钥库
        System.out.println("正在生成密钥库...");
        executeCommand(new String[]{
                "keytool", "-genkeypair",
                "-keysize", "1024",
                "-validity", "3650",
                "-alias", "privateKey",
                "-keystore", "privateKeys.keystore",
                "-storepass", publicPassword,
                "-keypass",  privatePassword,
                "-dname", "CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN",
                "-keyalg", "RSA",
                "-storetype", "JKS"
        });

        // 2. 导出证书
        System.out.println("正在导出证书...");
        executeCommand(new String[]{
                "keytool", "-exportcert",
                "-alias", "privateKey",
                "-keystore", "privateKeys.keystore",
                "-storepass", publicPassword,
                "-file", "certfile.cer",
                "-rfc"  // 以PEM格式输出
        });

        // 3. 导入证书
        System.out.println("正在导入证书...");
        executeCommand(new String[]{
                "keytool", "-import",
                "-alias", "publicCert",
                "-file", "certfile.cer",
                "-keystore", "publicCerts.keystore",
                "-storepass", publicPassword,
                "-noprompt"  // 不提示确认
        });

        System.out.println("所有操作完成！");
    }
}