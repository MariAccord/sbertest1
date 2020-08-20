package ru.allergiya.sbertest1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.allergiya.sbertest1.controller.IncrementController;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class IncrementService {
    private static Logger LOG = LoggerFactory.getLogger(IncrementService.class);
    //можно вынести в конфиг, но в рамках данной задачи оставляем так
    private static final int DEFAULT_DIFF = 1;
    private static final String IN_DELIMETER = ",";
    private static final String OUT_DELIMETER = ", ";
    private volatile int diff;

    @PostConstruct
    public void init() {
        diff = DEFAULT_DIFF;
    }

    public String increment(String numbers) throws RuntimeException {
        String[] numbersArray = numbers.split(IN_DELIMETER);
        StringBuilder sb = new StringBuilder();
        try {
            Arrays.stream(numbersArray)
                    //т.к. Integer.MAX + 1 = Integer.MIN, следует использовать BigInteger
                    //в рамках задачи BigInteger использовать не будем
                    .map(s -> Integer.parseInt(s.trim()) + diff)
                    .forEach(num -> sb.append(num).append(OUT_DELIMETER));
        } catch (NumberFormatException e) {
            LOG.error("Error parsing string numbers : {}", numbers);
            throw new RuntimeException(e);
        }
        sb.delete(sb.length()-2, sb.length());
        return sb.toString();
    }

    public synchronized void setDiff(int diff) {
        this.diff = diff;
    }

}
