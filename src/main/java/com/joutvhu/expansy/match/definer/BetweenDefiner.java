package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.NodeImpl;
import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import com.joutvhu.expansy.match.consumer.TrackPoints;
import com.joutvhu.expansy.parser.Analyser;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S3776")
public final class BetweenDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, BetweenDefiner<E, T>> {
    private final T parent;
    private final Integer minRepetitions;
    private final Integer maxRepetitions;
    private IsDefiner<E, T> between;

    public BetweenDefiner(T parent) {
        this(parent, 0, null);
    }

    public BetweenDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public BetweenDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        if (minRepetitions != null && minRepetitions < 0)
            throw new DefineException("The minRepetitions cannot be less than 0.");
        if (maxRepetitions != null && maxRepetitions < 1)
            throw new DefineException("The maxRepetitions cannot be less than 1.");
        this.minRepetitions = minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    @SuppressWarnings("java:S127")
    public Matcher<E> matcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                List<Matcher<E>> bm = between.matchers();
                List<TrackPoints<E>> cases = new ArrayList<>();
                TrackPoints<E> trackPoints = new TrackPoints<>();
                cases.add(trackPoints);

                List<NodeImpl<E>> nodes = new ArrayList<>();
                NodeImpl<E> node = new NodeImpl<>(consumer.state());
                node.setStart(consumer.offset());
                node.setEnd(consumer.offset());
                node.setValue("");
                node.setTrackPoints(trackPoints);
                nodes.add(node);

                if (minRepetitions != null && minRepetitions == 0) {
                    trackPoints.push(new TrackPoint<>(node));
                    consumer.setCases(cases);
                }
                Analyser<E> analyser = consumer.state().getAnalyser();
                for (int i = 0, r = 0; true; i++) {
                    boolean p = (i & 1) == 0;
                    try {
                        nodes = analyser.analyseMatchers(p ? matchers : bm, nodes, consumer.branch());
                        if (p) {
                            r++;
                            if (!nodes.isEmpty() && (minRepetitions == null || minRepetitions <= r)) {
                                cases = new ArrayList<>();
                                for (NodeImpl<E> eNode : nodes) {
                                    trackPoints = eNode.getTrackPoints().clone();
                                    trackPoints.push(new TrackPoint<>(eNode));
                                    eNode.setTrackPoints(trackPoints);
                                    cases.add(trackPoints);
                                }
                                consumer.setCases(cases);
                            }
                            if (maxRepetitions != null && maxRepetitions >= r)
                                consumer.close();
                        }
                    } catch (MatchException e) {
                        consumer.errorAt(e.getMessage(), e.getIndex(), e.getContent());
                    }
                }
            }
        };
    }

    public IsDefiner<E, T> is() {
        between = new IsDefiner<>(parent);
        return between;
    }

    @Override
    public OrDefiner<E, BetweenDefiner<E, T>> or() {
        return (OrDefiner<E, BetweenDefiner<E, T>>) super.or();
    }

    public class IsDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, IsDefiner<E, T>> {
        private T parent;

        public IsDefiner(T parent) {
            this.parent = parent;
        }

        @Override
        public OrDefiner<E, IsDefiner<E, T>> or() {
            return (OrDefiner<E, IsDefiner<E, T>>) super.or();
        }

        public T end() {
            return parent;
        }

        @Override
        public Matcher<E> matcher() {
            return null;
        }
    }
}
