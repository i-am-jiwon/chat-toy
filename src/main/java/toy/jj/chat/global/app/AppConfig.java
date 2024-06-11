package toy.jj.chat.global.app;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Getter
    public static String jwtSecretKey;

    @Value("111111asdfasdfasdfasdfasdfasdfasdfasdffasdfasdfasdfasdfasdfasdffasdfasdfasdfasdfasdfasdffasdfasdfasdfasdfasdfasdf")
    public void setJwtSecretKey(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    @Getter
    private static long accessTokenExpirationSec;

    @Value("3600")
    public void setJwtSecretKey(long accessTokenExpirationSec) {
        this.accessTokenExpirationSec = accessTokenExpirationSec;
    }

    @Getter
    private static String siteFrontUrl;

    @Value("localhost:3000")
    public void setSiteFrontUrl(String siteFrontUrl) {
        this.siteFrontUrl = siteFrontUrl;
    }

    @Getter
    private static String siteBackUrl;

    @Value("localhost:8090")
    public void setSiteBackUrl(String siteBackUrl) {
        this.siteBackUrl = siteBackUrl;
    }

    @Getter
    private static String siteCookieDomain;

    @Value("localhost")
    public void setSiteCookieDomain(String siteCookieDomain) {
        this.siteCookieDomain = siteCookieDomain;
    }
;

//    @Value("${custom.site.cookieDomain}")
//    public void setSiteCookieDomain(String siteCookieDomain) {
//        this.siteCookieDomain = siteCookieDomain;
//    }

    private static String resourcesStaticDirPath;

    public static String getResourcesStaticDirPath() {
        if (resourcesStaticDirPath == null) {
            ClassPathResource resource = new ClassPathResource("static/");
            try {
                resourcesStaticDirPath = resource.getFile().getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return resourcesStaticDirPath;
    }

    @Getter
    public static String tempDirPath;


    @Getter
    public static String siteName;

    @Getter
    public static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Getter
    public static int basePageSize = 10;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Getter
    public static String apiServiceKey;


    public static InputStream getResourceAsStream(String relativePath) {
        return AppConfig.class.getClassLoader().getResourceAsStream(relativePath);
    }

    public static String getResourcesAbsouluteDirPath(String path) {
        try {
            return new ClassPathResource(path).getFile().getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isAppInitialized = false;


}