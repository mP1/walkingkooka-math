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

import walkingkooka.collect.list.ImmutableListDefaults;
import walkingkooka.collect.list.Lists;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * An immutable list of {@link Number} that allows null elements.
 */
public final class NumberList extends AbstractList<Number>
    implements ImmutableListDefaults<NumberList, Number> {

    /**
     * An empty {@link NumberList}.
     */
    public final static NumberList EMPTY = new NumberList(
        Lists.empty()
    );

    /**
     * Factory that creates a {@link NumberList} from the list of {@link Number numbers}.
     */
    public static NumberList with(final Collection<Number> numbers) {
        Objects.requireNonNull(numbers, "numbers");

        NumberList DateList;

        if (numbers instanceof NumberList) {
            DateList = (NumberList) numbers;
        } else {
            final List<Number> copy = Lists.array();
            for (final Number number : numbers) {
                copy.add(number);
            }

            switch (numbers.size()) {
                case 0:
                    DateList = EMPTY;
                    break;
                default:
                    DateList = new NumberList(copy);
                    break;
            }
        }

        return DateList;
    }

    private NumberList(final List<Number> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Number get(int index) {
        return this.numbers.get(index);
    }

    @Override
    public int size() {
        return this.numbers.size();
    }

    private final List<Number> numbers;

    @Override
    public void elementCheck(final Number number) {
        // nulls are allowed.
    }

    @Override
    public NumberList setElements(final Collection<Number> numbers) {
        final NumberList copy = with(numbers);
        return this.equals(copy) ?
            this :
            copy;
    }
}
