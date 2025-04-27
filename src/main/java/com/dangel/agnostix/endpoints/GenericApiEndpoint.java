package com.dangel.agnostix.endpoints;

import com.dangel.agnostix.enums.ExchangeSources;
import com.dangel.agnostix.services.LocalCacheService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exchange")
public class GenericApiEndpoint {

    private LocalCacheService localCacheService;

    public GenericApiEndpoint(LocalCacheService localCacheService) {
        this.localCacheService = localCacheService;
    }


    @GetMapping("/keys")
    public String getPossibleKeys(Model model) {
        model.addAttribute("loadedKeys", localCacheService.getExchangeMap().keySet());
        model.addAttribute("possibleKeys", localCacheService.getPossibleSources());
        return "keys";
    }
}
