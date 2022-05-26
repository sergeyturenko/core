package net.betvictor.core.restclient;

import feign.Response;
import net.betvictor.core.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "account-service",
        url = "${feign.loripsum.url}",
        configuration = FeignClientConfig.class)
public interface LoripsumClient {

    @GetMapping("/api/{paraphCount}/{textLength}")
    Response getTextResponse(@PathVariable("paraphCount") int paraphCount,
                             @PathVariable("textLength") String textLength);

}
