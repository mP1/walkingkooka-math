package walkingkooka.math;

import walkingkooka.collect.list.ImmutableListDefaults;
import walkingkooka.collect.list.Lists;

import java.time.*;
import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

/**
 * An immutable list of {@link Number}.
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
     * Factory that creates a {@link NumberList} from the list of {@link Number times}.
     */
    public static NumberList with(final List<Number> times) {
        Objects.requireNonNull(times, "times");

        NumberList DateList;

        if (times instanceof NumberList) {
            DateList = (NumberList) times;
        } else {
            final List<Number> copy = Lists.array();
            for (final Number name : times) {
                copy.add(
                    Objects.requireNonNull(name, "includes null " + Number.class.getSimpleName())
                );
            }

            switch (times.size()) {
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

    private NumberList(final List<Number> times) {
        this.times = times;
    }

    @Override
    public Number get(int index) {
        return this.times.get(index);
    }

    @Override
    public int size() {
        return this.times.size();
    }

    private final List<Number> times;

    @Override
    public void elementCheck(final Number time) {
        Objects.requireNonNull(time, "time");
    }

    @Override
    public NumberList setElements(final List<Number> times) {
        final NumberList copy = with(times);
        return this.equals(copy) ?
            this :
            copy;
    }
}
