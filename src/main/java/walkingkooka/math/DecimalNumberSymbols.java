/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.math;

import walkingkooka.InvalidCharacterException;
import walkingkooka.InvalidTextException;
import walkingkooka.ToStringBuilder;
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.HasCsvStringList;
import walkingkooka.collect.list.HasTsvStringList;
import walkingkooka.collect.list.StringLikeList;
import walkingkooka.collect.list.TsvStringList;
import walkingkooka.predicate.Predicates;
import walkingkooka.predicate.character.CharPredicate;
import walkingkooka.predicate.character.CharPredicates;
import walkingkooka.props.HasProperties;
import walkingkooka.props.Properties;
import walkingkooka.props.PropertiesPath;
import walkingkooka.text.CharSequences;
import walkingkooka.text.HasText;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.TreePrintable;

import java.text.DecimalFormatSymbols;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Holds locale sensitive symbols related to the text representation or formatting of a number into text.
 */
public final class DecimalNumberSymbols implements DecimalNumberSymbolsLike,
    TreePrintable,
    HasCsvStringList,
    HasTsvStringList,
    HasDecimalNumberSymbols,
    HasProperties,
    HasText {

    static final String NEGATIVE_SIGN_LABEL = "negativeSign";
    static final String POSITIVE_SIGN_LABEL = "positiveSign";
    static final String ZERO_DIGIT_LABEL = "zeroDigit";

    static final String CURRENCY_SYMBOL_LABEL = "currencySymbol";
    static final String DECIMAL_SEPARATOR_LABEL = "decimalSeparator";
    static final String EXPONENT_SYMBOL_LABEL = "exponentSymbol";
    static final String GROUP_SEPARATOR_LABEL = "groupSeparator";
    static final String INFINITY_SYMBOL_LABEL = "infinitySymbol";
    static final String MONETARY_DECIMAL_SEPARATOR_LABEL = "monetaryDecimalSeparator";
    static final String NAN_SYMBOL_LABEL = "nanSymbol";
    static final String PERCENT_SYMBOL_LABEL = "percentSymbol";
    static final String PERMILL_SYMBOL_LABEL = "permillSymbol";

    /**
     * Parses the {@link String csv text} with each token interpreted as a character or string for each
     * of the {@link DecimalNumberSymbols} properties. This is the inverse of {@link #text()}.
     */
    public static DecimalNumberSymbols parse(final String text) {
        return fromStringsLikeList(
            CsvStringList.parse(text)
        );
    }

    public static DecimalNumberSymbols fromStringsLikeList(final StringLikeList strings) {
        Objects.requireNonNull(strings, "strings");

        final int tokenCount = strings.size();
        if (12 != tokenCount) {
            throw new IllegalArgumentException("Expected 12 tokens but got " + tokenCount);
        }

        return with(
            strings.get(0),
            strings.get(1),
            strings.getCharacter(2),
            strings.get(3), // currencySymbol
            strings.get(4),
            strings.get(5), // exponentSymbol
            strings.get(6),
            strings.get(7), // infinitySymbol
            strings.get(8),
            strings.get(9), // nanSymbol
            strings.get(10),
            strings.get(11)
        );
    }

    public static DecimalNumberSymbols fromDecimalFormatSymbols(final String positiveSign,
                                                                final DecimalFormatSymbols symbols) {
        Objects.requireNonNull(symbols, "symbols");

        return with(
            String.valueOf(
                symbols.getMinusSign()
            ),
            positiveSign,
            symbols.getZeroDigit(),
            symbols.getCurrencySymbol(),
            String.valueOf(
                symbols.getDecimalSeparator()
            ),
            symbols.getExponentSeparator(),
            String.valueOf(
                symbols.getGroupingSeparator()
            ),
            symbols.getInfinity(),
            String.valueOf(
                symbols.getMonetaryDecimalSeparator()
            ),
            symbols.getNaN(),
            String.valueOf(
                symbols.getPercent()
            ),
            String.valueOf(
                symbols.getPerMill()
            )
        );
    }


    public static DecimalNumberSymbols with(final String negativeSign,
                                            final String positiveSign,
                                            final char zeroDigit,
                                            final String currencySymbol,
                                            final String decimalSeparator,
                                            final String exponentSymbol,
                                            final String groupSeparator,
                                            final String infinitySymbol,
                                            final String monetaryDecimalSeparator,
                                            final String nanSymbol,
                                            final String percentSymbol,
                                            final String permillSymbol) {
        return new DecimalNumberSymbols(
            checkSymbol(NEGATIVE_SIGN_LABEL, negativeSign),
            checkSymbol(POSITIVE_SIGN_LABEL, positiveSign),
            checkZeroDigit(zeroDigit),
            checkString(CURRENCY_SYMBOL_LABEL, currencySymbol),
            checkSymbol(DECIMAL_SEPARATOR_LABEL, decimalSeparator),
            checkString(EXPONENT_SYMBOL_LABEL, exponentSymbol),
            checkSymbol(GROUP_SEPARATOR_LABEL, groupSeparator),
            checkString(INFINITY_SYMBOL_LABEL, infinitySymbol),
            checkSymbol(MONETARY_DECIMAL_SEPARATOR_LABEL, monetaryDecimalSeparator),
            checkString(NAN_SYMBOL_LABEL, nanSymbol),
            checkSymbol(PERCENT_SYMBOL_LABEL, percentSymbol),
            checkPermillSymbol(permillSymbol)
        );
    }

    private DecimalNumberSymbols(final String negativeSign,
                                 final String positiveSign,
                                 final char zeroDigit,
                                 final String currencySymbol,
                                 final String decimalSeparator,
                                 final String exponentSymbol,
                                 final String groupSeparator,
                                 final String infinitySymbol,
                                 final String monetaryDecimalSeparator,
                                 final String nanSymbol,
                                 final String percentSymbol,
                                 final String permillSymbol) {
        this.negativeSign = negativeSign;
        this.positiveSign = positiveSign;
        this.zeroDigit = zeroDigit;

        this.decimalSeparator = decimalSeparator;

        this.groupSeparator = groupSeparator;
        this.monetaryDecimalSeparator = monetaryDecimalSeparator;
        this.percentSymbol = percentSymbol;
        this.permillSymbol = permillSymbol;

        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, positiveSign, POSITIVE_SIGN_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, decimalSeparator, DECIMAL_SEPARATOR_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, groupSeparator, GROUP_SEPARATOR_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(negativeSign, NEGATIVE_SIGN_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, decimalSeparator, DECIMAL_SEPARATOR_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, groupSeparator, GROUP_SEPARATOR_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(positiveSign, POSITIVE_SIGN_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(decimalSeparator, DECIMAL_SEPARATOR_LABEL, groupSeparator, GROUP_SEPARATOR_LABEL);
        // decimalSeparator can be same as monetaryDecimalSeparator
        failIfEqual(decimalSeparator, DECIMAL_SEPARATOR_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(decimalSeparator, DECIMAL_SEPARATOR_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(groupSeparator, GROUP_SEPARATOR_LABEL, monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL);
        failIfEqual(groupSeparator, GROUP_SEPARATOR_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(groupSeparator, GROUP_SEPARATOR_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL, percentSymbol, PERCENT_SYMBOL_LABEL);
        failIfEqual(monetaryDecimalSeparator, MONETARY_DECIMAL_SEPARATOR_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        failIfEqual(percentSymbol, PERCENT_SYMBOL_LABEL, permillSymbol, PERMILL_SYMBOL_LABEL);

        this.currencySymbol = Objects.requireNonNull(currencySymbol, CURRENCY_SYMBOL_LABEL);
        this.exponentSymbol = Objects.requireNonNull(exponentSymbol, EXPONENT_SYMBOL_LABEL);
        this.infinitySymbol = infinitySymbol;
        this.nanSymbol = nanSymbol;
    }

    private static void failIfEqual(final String left,
                                    final String leftLabel,
                                    final String right,
                                    final String rightLabel) {
        if (left.equals(right)) {
            throw new InvalidTextException(
                left,
                "Duplicate " +
                    CharSequences.quoteAndEscape(rightLabel) +
                    " is same as " +
                    CharSequences.quoteAndEscape(leftLabel) +
                    " " +
                    CharSequences.quoteAndEscape(left)
            ).setLabel(
                Optional.of(rightLabel)
            );
        }
    }

    // negativeSign.....................................................................................................

    /**
     * Returns the negative sign.
     */
    @Override
    public String negativeSign() {
        return this.negativeSign;
    }

    public DecimalNumberSymbols setNegativeSign(final String negativeSign) {
        return this.negativeSign.equals(negativeSign) ?
            this :
            new DecimalNumberSymbols(
                checkString(NEGATIVE_SIGN_LABEL, negativeSign),
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String negativeSign;

    // positiveSign.....................................................................................................

    /**
     * Returns the positive sign.
     */
    @Override
    public String positiveSign() {
        return this.positiveSign;
    }

    public DecimalNumberSymbols setPositiveSign(final String positiveSign) {
        return this.positiveSign.equals(positiveSign) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                checkSymbol(POSITIVE_SIGN_LABEL, positiveSign),
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String positiveSign;

    // zeroDigit........................................................................................................

    /**
     * Returns the zero digit.
     */
    @Override
    public char zeroDigit() {
        return this.zeroDigit;
    }

    public DecimalNumberSymbols setZeroDigit(final char zeroDigit) {
        return this.zeroDigit == zeroDigit ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                checkZeroDigit(zeroDigit),
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final char zeroDigit;

    // currencySymbol...................................................................................................

    /**
     * The currency symbol character.
     */
    @Override
    public String currencySymbol() {
        return this.currencySymbol;
    }

    public DecimalNumberSymbols setCurrencySymbol(final String currencySymbol) {
        return this.currencySymbol.equals(currencySymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                checkString(CURRENCY_SYMBOL_LABEL, currencySymbol),
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String currencySymbol;

    // decimalSeparator.................................................................................................

    /**
     * Returns the decimal separator character
     */
    @Override
    public String decimalSeparator() {
        return this.decimalSeparator;
    }

    public DecimalNumberSymbols setDecimalSeparator(final String decimalSeparator) {
        return this.decimalSeparator.equals(decimalSeparator) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                checkSymbol(DECIMAL_SEPARATOR_LABEL, decimalSeparator),
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String decimalSeparator;

    // exponentSymbol...................................................................................................

    /**
     * The exponent symbol
     */
    @Override
    public String exponentSymbol() {
        return this.exponentSymbol;
    }

    public DecimalNumberSymbols setExponentSymbol(final String exponentSymbol) {
        return this.exponentSymbol.equals(exponentSymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                checkString(EXPONENT_SYMBOL_LABEL, exponentSymbol),
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String exponentSymbol;

    // groupSeparator...................................................................................................

    /**
     * The group separator.
     */
    @Override
    public String groupSeparator() {
        return this.groupSeparator;
    }

    public DecimalNumberSymbols setGroupSeparator(final String groupSeparator) {
        return this.groupSeparator.equals(groupSeparator) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                checkSymbol(GROUP_SEPARATOR_LABEL, groupSeparator),
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String groupSeparator;

    // infinitySymbol.........................................................................................................

    /**
     * The INFINITY symbol.
     */
    @Override
    public String infinitySymbol() {
        return this.infinitySymbol;
    }

    public DecimalNumberSymbols setInfinitySymbol(final String infinitySymbol) {
        return this.infinitySymbol.equals(infinitySymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                checkString(INFINITY_SYMBOL_LABEL, infinitySymbol),
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String infinitySymbol;

    // monetaryDecimalSeparator.........................................................................................

    /**
     * Returns the monetary decimal separator character
     */
    @Override
    public String monetaryDecimalSeparator() {
        return this.monetaryDecimalSeparator;
    }

    public DecimalNumberSymbols setMonetaryDecimalSeparator(final String monetaryDecimalSeparator) {
        return this.monetaryDecimalSeparator.equals(monetaryDecimalSeparator) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                checkSymbol(MONETARY_DECIMAL_SEPARATOR_LABEL, monetaryDecimalSeparator),
                this.nanSymbol,
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String monetaryDecimalSeparator;

    // nanSymbol........................................................................................................

    /**
     * The NAN symbol.
     */
    @Override
    public String nanSymbol() {
        return this.nanSymbol;
    }

    public DecimalNumberSymbols setNanSymbol(final String nanSymbol) {
        return this.nanSymbol.equals(nanSymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                checkString(NAN_SYMBOL_LABEL, nanSymbol),
                this.percentSymbol,
                this.permillSymbol
            );
    }

    private final String nanSymbol;

    // percentSymbol.................................................................................................

    /**
     * The percent symbol.
     */
    @Override
    public String percentSymbol() {
        return this.percentSymbol;
    }

    public DecimalNumberSymbols setPercentSymbol(final String percentSymbol) {
        return this.percentSymbol.equals(percentSymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                checkSymbol(PERCENT_SYMBOL_LABEL, percentSymbol),
                this.permillSymbol
            );
    }

    private final String percentSymbol;

    // permillSymbol....................................................................................................

    /**
     * The permill symbol.
     */
    @Override
    public String permillSymbol() {
        return this.permillSymbol;
    }

    public DecimalNumberSymbols setPermillSymbol(final String permillSymbol) {
        return this.permillSymbol.equals(permillSymbol) ?
            this :
            new DecimalNumberSymbols(
                this.negativeSign,
                this.positiveSign,
                this.zeroDigit,
                this.currencySymbol,
                this.decimalSeparator,
                this.exponentSymbol,
                this.groupSeparator,
                this.infinitySymbol,
                this.monetaryDecimalSeparator,
                this.nanSymbol,
                this.percentSymbol,
                checkSymbol(PERMILL_SYMBOL_LABEL, permillSymbol)
            );
    }

    private final String permillSymbol;

    // helper...........................................................................................................

    private final static CharPredicate PRINTABLE = CharPredicates.asciiControl()
        .negate();

    public final static CharPredicate SYMBOL = PRINTABLE.and(
        CharPredicates.letterOrDigit()
            .negate()
    ).and(
        CharPredicates.whitespace()
            .negate()
    ).setToString("symbol");

    /**
     * A {@link Predicate} that may be used to test if a {@link CharSequence} is a valid PERMILL symbol.
     */
    public final static CharPredicate PERMILL_SYMBOL = PRINTABLE.and(
        CharPredicates.letter()
            .negate()
    ).and(
        CharPredicates.whitespace()
            .negate()
    ).setToString(PERMILL_SYMBOL_LABEL);

    private static String checkPermillSymbol(final String permillSymbol) {
        int i = 0;

        for (final char c : permillSymbol.toCharArray()) {
            if (false == PERMILL_SYMBOL.test(c)) {
                throw new InvalidCharacterException(PERMILL_SYMBOL_LABEL, i);
            }
            i++;
        }
        return permillSymbol;
    }

    private static String checkString(final String label,
                                      final String value) {
        return CharPredicates.failIfNullOrEmptyOrInitialAndPartFalse(
            value,
            label,
            PRINTABLE,
            PRINTABLE
        );
    }

    private static String checkSymbol(final String label,
                                      final String value) {
        return CharPredicates.failIfNullOrEmptyOrInitialAndPartFalse(
            value,
            label,
            SYMBOL,
            SYMBOL
        );
    }

    /**
     * This {@link Predicate} may be used to verify any of the {@link String} properties of a {@link DecimalNumberSymbols}
     * such as {@link DecimalNumberSymbols#currencySymbol()}, {@link DecimalNumberSymbols#infinitySymbol()}, {@link DecimalNumberSymbols#nanSymbol()}.
     */
    public final static Predicate<CharSequence> STRING = Predicates.initialAndPart(
        PRINTABLE,
        PRINTABLE
    );

    /**
     * May be used to test if the given character is a valid zero digit.
     */
    public final static CharPredicate ZERO_DIGIT = CharPredicates.digit()
        .setToString(ZERO_DIGIT_LABEL);

    private static char checkZeroDigit(final char zeroDigit) {
        if (false == ZERO_DIGIT.test(zeroDigit)) {
            throw new InvalidCharacterException(ZERO_DIGIT_LABEL, zeroDigit);
        }

        return zeroDigit;
    }

    // HasText..........................................................................................................

    /**
     * Returns a CSV string with the individual symbols.
     */
    @Override
    public String text() {
        return CsvStringList.EMPTY.concat(this.negativeSign)
            .concat(this.positiveSign)
            .concat(
                String.valueOf(this.zeroDigit)
            ).concat(this.currencySymbol)
            .concat(this.decimalSeparator)
            .concat(this.exponentSymbol)
            .concat(this.groupSeparator)
            .concat(this.infinitySymbol)
            .concat(this.monetaryDecimalSeparator)
            .concat(this.nanSymbol)
            .concat(this.percentSymbol)
            .concat(this.permillSymbol)
            .text();
    }

    // fromProperties...................................................................................................

    public static DecimalNumberSymbols fromProperties(final Properties properties) {
        Objects.requireNonNull(properties, "properties");

        return with(
            properties.getOrFail(NEGATIVE_SIGN_PROPERTIES_KEY),
            properties.getOrFail(POSITIVE_SIGN_PROPERTIES_KEY),
            character(
                properties,
                ZERO_DIGIT_PROPERTIES_KEY
            ),
            properties.getOrFail(CURRENCY_SYMBOL_PROPERTIES_KEY),
            properties.getOrFail(DECIMAL_SEPARATOR_PROPERTIES_KEY),
            properties.getOrFail(EXPONENT_SYMBOL_PROPERTIES_KEY),
            properties.getOrFail(GROUP_SEPARATOR_PROPERTIES_KEY),
            properties.getOrFail(INFINITY_SYMBOL_PROPERTIES_KEY),
            properties.getOrFail(MONETARY_DECIMAL_SEPARATOR_PROPERTIES_KEY),
            properties.getOrFail(NAN_SYMBOL_PROPERTIES_KEY),
            properties.getOrFail(PERCENT_SYMBOL_PROPERTIES_KEY),
            properties.getOrFail(PERMILL_SYMBOL_PROPERTIES_KEY)
        );
    }

    private static char character(final Properties properties,
                                  final PropertiesPath key) {
        final String stringValue = properties.getOrFail(key);
        if (stringValue.length() != 1) {
            throw new IllegalArgumentException("Invalid property " + key + " got " + CharSequences.quote(stringValue));
        }

        return stringValue.charAt(0);
    }

    // HasProperties....................................................................................................

    @Override
    public Properties properties() {
        return Properties.EMPTY.set(
            NEGATIVE_SIGN_PROPERTIES_KEY,
            this.negativeSign
        ).set(
            POSITIVE_SIGN_PROPERTIES_KEY,
            this.positiveSign
        ).set(
            ZERO_DIGIT_PROPERTIES_KEY,
            String.valueOf(this.zeroDigit)
        ).set(
            CURRENCY_SYMBOL_PROPERTIES_KEY,
            this.currencySymbol
        ).set(
            DECIMAL_SEPARATOR_PROPERTIES_KEY,
            this.decimalSeparator
        ).set(
            EXPONENT_SYMBOL_PROPERTIES_KEY,
            this.exponentSymbol
        ).set(
            GROUP_SEPARATOR_PROPERTIES_KEY,
            this.groupSeparator
        ).set(
            INFINITY_SYMBOL_PROPERTIES_KEY,
            this.infinitySymbol
        ).set(
            MONETARY_DECIMAL_SEPARATOR_PROPERTIES_KEY,
            this.monetaryDecimalSeparator
        ).set(
            NAN_SYMBOL_PROPERTIES_KEY,
            this.nanSymbol
        ).set(
            PERCENT_SYMBOL_PROPERTIES_KEY,
            this.percentSymbol
        ).set(
            PERMILL_SYMBOL_PROPERTIES_KEY,
            this.permillSymbol
        );
    }

    private final static PropertiesPath NEGATIVE_SIGN_PROPERTIES_KEY = PropertiesPath.parse("negativeSign");
    private final static PropertiesPath POSITIVE_SIGN_PROPERTIES_KEY = PropertiesPath.parse("positiveSign");
    private final static PropertiesPath ZERO_DIGIT_PROPERTIES_KEY = PropertiesPath.parse("zeroDigit");
    private final static PropertiesPath CURRENCY_SYMBOL_PROPERTIES_KEY = PropertiesPath.parse("currencySymbol");
    private final static PropertiesPath DECIMAL_SEPARATOR_PROPERTIES_KEY = PropertiesPath.parse("decimalSeparator");
    private final static PropertiesPath EXPONENT_SYMBOL_PROPERTIES_KEY = PropertiesPath.parse("exponentSymbol");
    private final static PropertiesPath GROUP_SEPARATOR_PROPERTIES_KEY = PropertiesPath.parse("groupSeparator");
    private final static PropertiesPath INFINITY_SYMBOL_PROPERTIES_KEY = PropertiesPath.parse("infinitySymbol");
    private final static PropertiesPath MONETARY_DECIMAL_SEPARATOR_PROPERTIES_KEY = PropertiesPath.parse("monetaryDecimalSeparator");
    private final static PropertiesPath NAN_SYMBOL_PROPERTIES_KEY = PropertiesPath.parse("nanSymbol");
    private final static PropertiesPath PERCENT_SYMBOL_PROPERTIES_KEY = PropertiesPath.parse("percentSymbol");
    private final static PropertiesPath PERMILL_SYMBOL_PROPERTIES_KEY = PropertiesPath.parse("permillSymbol");

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hash(
            this.negativeSign,
            this.positiveSign,
            this.zeroDigit,
            this.currencySymbol,
            this.decimalSeparator,
            this.exponentSymbol,
            this.groupSeparator,
            this.infinitySymbol,
            this.monetaryDecimalSeparator,
            this.nanSymbol,
            this.percentSymbol,
            this.permillSymbol
        );
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof DecimalNumberSymbols && this.equals0((DecimalNumberSymbols) other);
    }

    private boolean equals0(final DecimalNumberSymbols other) {
        return this.negativeSign.equals(other.negativeSign) &&
            this.positiveSign.equals(other.positiveSign) &&
            this.zeroDigit == other.zeroDigit &&
            this.currencySymbol.equals(other.currencySymbol) &&
            this.decimalSeparator.equals(other.decimalSeparator) &&
            this.exponentSymbol.equals(other.exponentSymbol) &&
            this.groupSeparator.equals(other.groupSeparator) &&
            this.infinitySymbol.equals(other.infinitySymbol) &&
            this.monetaryDecimalSeparator.equals(other.monetaryDecimalSeparator) &&
            this.nanSymbol.equals(other.nanSymbol) &&
            this.percentSymbol.equals(other.percentSymbol) &&
            this.permillSymbol.equals(other.permillSymbol);
    }

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .label(NEGATIVE_SIGN_LABEL).value(this.negativeSign)
            .label(POSITIVE_SIGN_LABEL).value(this.positiveSign)
            .label(ZERO_DIGIT_LABEL).value(this.zeroDigit)
            .label(CURRENCY_SYMBOL_LABEL).value(this.currencySymbol)
            .label(DECIMAL_SEPARATOR_LABEL).value(this.decimalSeparator)
            .label(EXPONENT_SYMBOL_LABEL).value(this.exponentSymbol)
            .label(GROUP_SEPARATOR_LABEL).value(this.groupSeparator)
            .label(INFINITY_SYMBOL_LABEL).value(this.infinitySymbol)
            .label(MONETARY_DECIMAL_SEPARATOR_LABEL).value(this.monetaryDecimalSeparator)
            .label(NAN_SYMBOL_LABEL).value(this.nanSymbol)
            .label(PERCENT_SYMBOL_LABEL).value(this.percentSymbol)
            .label(PERMILL_SYMBOL_LABEL).value(this.permillSymbol)
            .build();
    }

    // TreePrintable....................................................................................................

    @Override
    public void printTree(IndentingPrinter printer) {
        printer.println(this.getClass().getSimpleName());

        printer.indent();
        {
            this.printLabelAndValues(
                NEGATIVE_SIGN_LABEL,
                this.negativeSign,
                printer
            );
            this.printLabelAndValues(
                POSITIVE_SIGN_LABEL,
                this.positiveSign,
                printer
            );
            this.printLabelAndValues(
                ZERO_DIGIT_LABEL,
                this.zeroDigit,
                printer
            );
            this.printLabelAndValues(
                CURRENCY_SYMBOL_LABEL,
                this.currencySymbol,
                printer
            );
            this.printLabelAndValues(
                DECIMAL_SEPARATOR_LABEL,
                this.decimalSeparator,
                printer
            );
            this.printLabelAndValues(
                EXPONENT_SYMBOL_LABEL,
                this.exponentSymbol,
                printer
            );
            this.printLabelAndValues(
                GROUP_SEPARATOR_LABEL,
                this.groupSeparator,
                printer
            );
            this.printLabelAndValues(
                INFINITY_SYMBOL_LABEL,
                this.infinitySymbol,
                printer
            );
            this.printLabelAndValues(
                MONETARY_DECIMAL_SEPARATOR_LABEL,
                this.monetaryDecimalSeparator,
                printer
            );
            this.printLabelAndValues(
                NAN_SYMBOL_LABEL,
                this.nanSymbol,
                printer
            );
            this.printLabelAndValues(
                PERCENT_SYMBOL_LABEL,
                this.percentSymbol,
                printer
            );
            this.printLabelAndValues(
                PERMILL_SYMBOL_LABEL,
                this.permillSymbol,
                printer
            );
        }
        printer.outdent();
    }

    private void printLabelAndValues(final String label,
                                     final Object value,
                                     final IndentingPrinter printer) {
        printer.println(label);

        printer.indent();
        {
            printer.println(
                CharSequences.quoteIfChars(value)
            );
        }
        printer.outdent();
    }

    // HasDecimalNumberSymbols..........................................................................................

    @Override
    public DecimalNumberSymbols decimalNumberSymbols() {
        return this;
    }

    // HasCsvStringList.................................................................................................

    @Override
    public CsvStringList csvStringList() {
        return CsvStringList.EMPTY.concat(this.negativeSign)
            .concat(this.positiveSign)
            .concat(
                String.valueOf(this.zeroDigit)
            ).concat(this.currencySymbol)
            .concat(
                String.valueOf(this.decimalSeparator)
            ).concat(this.exponentSymbol)
            .concat(
                String.valueOf(this.groupSeparator)
            ).concat(this.infinitySymbol)
            .concat(
                String.valueOf(this.monetaryDecimalSeparator)
            ).concat(this.nanSymbol)
            .concat(this.percentSymbol)
            .concat(this.permillSymbol);
    }

    // HasTsvStringList.................................................................................................

    @Override
    public TsvStringList tsvStringList() {
        return TsvStringList.EMPTY.setElements(this.csvStringList());
    }
}
