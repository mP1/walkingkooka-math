package walkingkooka.math;

import walkingkooka.collect.list.ImmutableListDefaults;
import walkingkooka.collect.list.Lists;

import java.util.AbstractList;
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
    public static NumberList with(final List<Number> numbers) {
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
    public NumberList setElements(final List<Number> numbers) {
        final NumberList copy = with(numbers);
        return this.equals(copy) ?
            this :
            copy;
    }
}
