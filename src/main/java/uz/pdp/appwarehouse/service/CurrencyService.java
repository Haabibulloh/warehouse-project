package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Currency;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CurrencyRepository;
import uz.pdp.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency) {
        boolean exists = currencyRepository.existsByName(currency.getName());
        if (exists) {
            return new Result("This Currency is already exist", false);
        }
        currencyRepository.save(currency);
        return new Result("This Currency is successfully added", true);
    }

    public List<Currency> getCurrencyService() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyByIdService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Currency();
        return optionalCurrency.get();
    }

    public Result deleteCurrencyService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent())
            return new Result("This Currency not found", false);
        currencyRepository.deleteById(id);
        return new Result("Currency successfully deleted!", true);
    }

    public Result updateCurrencyService(Currency currency, Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new Result("This Currency not found", false);
        }
        currencyRepository.save(currency);
        return new Result("This Currency is successfully updated", true);
    }
}
