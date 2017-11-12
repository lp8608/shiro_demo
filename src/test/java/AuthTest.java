import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthTest {
    private static Logger logger = LoggerFactory.getLogger(AuthTest.class);

    @Test
    public void authTest(){

        logger.info("test");
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:auth.ini");
        SecurityManager sm = factory.getInstance();
        SecurityUtils.setSecurityManager(sm);
        Subject user = SecurityUtils.getSubject();
        if(!user.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");
            user.login(token);
        }
        logger.info("当前登陆用户："+ user.getPrincipal());
        boolean hubband = user.hasRole("hubband");
        if(hubband){
            logger.info("this is husband!!!");
        }else{
            logger.info("this is not husband!!");
        }
    }

}
