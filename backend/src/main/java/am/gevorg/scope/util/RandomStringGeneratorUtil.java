package am.gevorg.scope.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomStringGeneratorUtil {
    public String uuId() {
        return generateRandomStringByUUID();
    }

    public static String generateRandomStringByUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
