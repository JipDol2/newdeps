package lotte.newdevps.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import jakarta.annotation.PreDestroy;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static java.lang.System.exit;

@Slf4j
@Profile(value = "local")
@Setter
@Component
@ConfigurationProperties(prefix = "ssh")
public class SshTunnelingInitializer {

    private String remoteJumpHost;
    private int sshPort;
    private String user;
    private String password;
    private int databasePort;

    private Session session;

    @PreDestroy
    public void closeSSH() {
        if (session.isConnected())
            session.disconnect();
    }

    public Integer buildSshConnection() {

        Integer forwardedPort = null;

        try {
            log.info("{}@{}:{}:{} with privateKey",user, remoteJumpHost, sshPort, databasePort);

            log.info("start ssh tunneling..");
            JSch jSch = new JSch();

            log.info("creating ssh session");
//            jSch.addIdentity(privateKey);  // 개인키
            session = jSch.getSession(user, remoteJumpHost, sshPort);  // 세션 설정
            session.setPassword(password);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            log.info("complete creating ssh session");
            log.info("start connecting ssh connection");
            session.connect();  // ssh 연결
            log.info("success connecting ssh connection ");

            // 로컬pc의 남는 포트 하나와 원격 접속한 pc의 db포트 연결
            log.info("start forwarding");
            forwardedPort = session.setPortForwardingL(3307, "ls-4fbbe9fdac5cee2838707289633a69dfbb72e0ce.cuixbagxdgr9.ap-northeast-2.rds.amazonaws.com", databasePort);
            log.info("successfully connected to database");

        } catch (Exception e){
            log.error("fail to make ssh tunneling");
            this.closeSSH();
            e.printStackTrace();
            exit(1);
        }

        return forwardedPort;
    }
}
