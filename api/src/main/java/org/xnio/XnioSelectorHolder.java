package org.xnio;

import java.nio.channels.Selector;

public class XnioSelectorHolder {

    private static final ThreadLocal<Selector> selectorThreadLocal = new ThreadLocal<Selector>() {
        public void remove() {
            // if no selector was created, none will be closed
            IoUtils.safeClose(get());
            super.remove();
        }
    };

    public static Selector get() {
        return selectorThreadLocal.get();
    }

    public static void set(Selector selector) {
        selectorThreadLocal.set(selector);
    }

    public static void clear() {
        selectorThreadLocal.remove();
    }
}

