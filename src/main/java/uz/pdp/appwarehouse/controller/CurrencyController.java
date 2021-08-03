package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CurrencyService;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Currency currency) {
        return currencyService.addCurrencyService(currency);
    }

    @GetMapping
    public List<Currency> getCurrencyController() {
        return currencyService.getCurrencyService();
    }

    @GetMapping("/{id}")
    public Currency getCurrencyByIdController(@PathVariable Integer id) {
        return currencyService.getCurrencyByIdService(id);
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrencyController(@PathVariable Integer id) {
        Result result = currencyService.deleteCurrencyService(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateCurrencyController(@RequestBody Currency currency, @PathVariable Integer id) {
        Result result = currencyService.updateCurrencyService(currency, id);
        return result;
    }
}
