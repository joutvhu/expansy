package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
import com.joutvhu.expansy.exception.MatchException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.TrackPoint;
import com.joutvhu.expansy.match.consumer.TrackPoints;
import com.joutvhu.expansy.parser.Analyser;

import java.util.ArrayList;
import java.util.List;

public final class BetweenDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, BetweenDefiner<E, T>> {
    private T parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;
    private IsDefiner<E, T> between;

    public BetweenDefiner(T parent) {
        this(parent, 0, null);
    }

    public BetweenDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public BetweenDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        this.minRepetitions = minRepetitions == null || minRepetitions < 0 ? 0 : minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                List<Matcher<E>> bm = between.matchers();
                List<TrackPoints<E>> trackPoints = new ArrayList<>();
                TrackPoints<E> points = new TrackPoints<>();
                trackPoints.add(points);

                List<Node<E>> nodes = new ArrayList<>();
                Node<E> node = new Node<>();
                node.setStart(consumer.offset());
                node.setEnd(consumer.offset());
                node.setValue("");
                nodes.add(node);
                node.setTrackPoints(points);

                if (minRepetitions != null && minRepetitions == 0) {
                    points.push(new TrackPoint<>(node));
                    consumer.setPointBranches(trackPoints);
                }
                Analyser<E> analyser = consumer.state().getAnalyser();
                for (int i = 0, r = 0; true; i++) {
                    boolean p = (i & 1) == 0;
                    try {
                        nodes = analyser.analyseMatchers(p ? matchers : bm, nodes, consumer.branch());
                        if (p) {
                            r++;
                            if (!nodes.isEmpty() && (minRepetitions == null || minRepetitions <= r)) {
                                trackPoints = new ArrayList<>();
                                for (Node<E> eNode : nodes) {
                                    points = eNode.getTrackPoints().clone();
                                    points.push(new TrackPoint<>(eNode));
                                    eNode.setTrackPoints(points);
                                    trackPoints.add(points);
                                }
                                consumer.setPointBranches(trackPoints);
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
