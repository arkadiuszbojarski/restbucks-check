package org.restbucks.view;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("http://check-view")
public interface ViewClient {

    @GetMapping
    String getLinks();
}
