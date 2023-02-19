package com.joutvhu.expansy.io;

import com.joutvhu.expansy.match.filter.Consumer;
import com.joutvhu.expansy.match.filter.StopPoint;
import org.apache.commons.lang3.StringUtils;

public class ProxySource implements Source {
    private long current;
    private Consumer consumer;
    private String buffer;

    public ProxySource(Consumer consumer) {
        this.current = 0;
        this.consumer = consumer;
        this.buffer = StringUtils.EMPTY;
    }

    @Override
    public void reset() {
        current = 0;
    }

    @Override
    public long back(long offset) {
        current = offset;
        return current;
    }

    @Override
    public String read(int length) {
        String result = read(current, length);
        current += result.length();
        return result;
    }

    @Override
    public String read(long offset, int length) {
        int len = (int) (offset + length - buffer.length());
        if (len > 0) {
            StopPoint point = consumer.next(len);
            buffer = point.getValue();
        }
        return buffer.substring((int) offset, length);
    }
}
