package com.instinctools.reducerlink.service.support;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class AppDecimalFormat extends DecimalFormat {
    private static final long serialVersionUID = 0L;

    public AppDecimalFormat() {
        super("0.00");
        init('.', '\u00a0');
    }

    public AppDecimalFormat(String pattern) {
        super(pattern);
        init('.', '\u00a0');
    }

    public AppDecimalFormat(String pattern, char decimalSeparator) {
        super(pattern);
        init(decimalSeparator, '\u00a0');
    }

    public AppDecimalFormat(String pattern, char decimalSeparator, char groupingSeparator) {
        super(pattern);
        init(decimalSeparator, groupingSeparator);
    }

    private void init(char decimalSeparator, char groupingSeparator) {
        DecimalFormatSymbols symbols = getDecimalFormatSymbols();
        symbols.setDecimalSeparator(decimalSeparator);
        symbols.setGroupingSeparator(groupingSeparator);
        setDecimalFormatSymbols(symbols);
    }
}
