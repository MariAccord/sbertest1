package ru.allergiya.sbertest1.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class IncrementService {
    //можно вынести в конфиг, но в рамках данной задачи оставляем так
    private static final int DEFAULT_DIFF = 1;
    private static final String IN_DELIMETER = ",";
    private static final String OUT_DELIMETER = ", ";
    private volatile int diff;

    @PostConstruct
    public void init() {
        diff = DEFAULT_DIFF;
    }

    public String increment(String numbers) {
        String[] numbersArray = numbers.split(IN_DELIMETER);
        StringBuilder sb = new StringBuilder();
        Arrays.stream(numbersArray)
                //т.к. Integer.MAX + 1 = Integer.MIN, следует использовать BigInteger
                //в рамках задачи BigInteger использовать не будем
                .map(s -> Integer.parseInt(s.trim()) + diff)
                .forEach(num -> sb.append(num).append(OUT_DELIMETER));
        sb.delete(sb.length()-2, sb.length());
        return sb.toString();
    }

    public synchronized void setDiff(int diff) {
        this.diff = diff;
    }

}
