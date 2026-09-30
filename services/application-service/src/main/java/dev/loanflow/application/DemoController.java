package dev.loanflow.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DemoController {

    private final List<byte[]> hold = Collections.synchronizedList(new ArrayList<>());

    @GetMapping("/hello")
    String hello() {
        return "hello";
    }

    @GetMapping("/slow")
    String slow(@RequestParam(defaultValue = "5000") long ms) throws InterruptedException {
        Thread.sleep(ms);
        return "done after " + ms + " ms";
    }

    @GetMapping("/alloc")
    String alloc(@RequestParam int mb) {
        hold.add(new byte[mb * 1024 * 1024]);
        return "holding " + hold.size() + " blocks";
    }
}
