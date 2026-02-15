package walkingkooka.math;


import walkingkooka.Value;
import walkingkooka.Cast;

import java.util.Objects;
import java.util.Optional;

/**
 * A typed {@link Optional} necessary because generic types are lost in java.
 */
public final class OptionalDecimalNumberSymbols implements Value<Optional<DecimalNumberSymbols>> {

    public final static OptionalDecimalNumberSymbols EMPTY = new OptionalDecimalNumberSymbols(Optional.empty());

    public static OptionalDecimalNumberSymbols with(final Optional<DecimalNumberSymbols> value) {
        Objects.requireNonNull(value, "value");

        return value.isPresent() ?
            new OptionalDecimalNumberSymbols(value) :
            EMPTY;
    }

    private OptionalDecimalNumberSymbols(final Optional<DecimalNumberSymbols> value) {
        this.value = value;
    }

    // Value............................................................................................................

    @Override
    public Optional<DecimalNumberSymbols> value() {
        return this.value;
    }

    private final Optional<DecimalNumberSymbols> value;

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return this == other ||
            other instanceof OptionalDecimalNumberSymbols &&
                this.equals0(
                    Cast.to(other)
                );
    }

    private boolean equals0(final OptionalDecimalNumberSymbols other) {
        return this.value.equals(other.value);
    }

    @Override
    public String toString() {
        return this.value.map(DecimalNumberSymbols::toString)
            .orElse("");
    }
}
