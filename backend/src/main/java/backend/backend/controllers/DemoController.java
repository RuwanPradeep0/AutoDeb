package backend.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/Demo")
    public ResponseEntity<String> getExample(){
        return ResponseEntity.ok("working properly");
    }
}


//@RestController
//public class DemoController {
//    @GetMapping("/example")
//    public ResponseEntity<String> getExample() {
//
//
//        return ResponseEntity.ok()
//                .cacheControl(CacheControl.noStore().mustRevalidate())
//                .body("Example response");
//
//    }
//}