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


import walkingkooka.Cast;
import walkingkooka.HasValue;

import java.util.Objects;
import java.util.Optional;

/**
 * A typed {@link Optional} necessary because generic types are lost in java.
 */
public final class OptionalDecimalNumberSymbols implements HasValue<Optional<DecimalNumberSymbols>> {

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
