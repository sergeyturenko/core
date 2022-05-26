package net.betvictor.core;

import net.betvictor.core.restclient.LoripsumService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CoreApplication.class)
class CoreApplicationTests {

    @Autowired
    private LoripsumService processingService;

    @Test
    void contextLoads() {
    }

}
