package com.joutvhu.expansy.match.definer;

import com.joutvhu.expansy.element.Node;
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

public final class LoopDefiner<E, T extends Definer<E>> extends ProxyDefiner<E, LoopDefiner<E, T>> {
    private T parent;
    private Integer minRepetitions;
    private Integer maxRepetitions;

    public LoopDefiner(T parent) {
        this(parent, 0, null);
    }

    public LoopDefiner(T parent, Integer repetitions) {
        this(parent, repetitions, repetitions);
    }

    public LoopDefiner(T parent, Integer minRepetitions, Integer maxRepetitions) {
        this.parent = parent;
        if (minRepetitions != null && minRepetitions < 0)
            throw new DefineException("The minRepetitions cannot be less than 0.");
        if (maxRepetitions != null && maxRepetitions < 1)
            throw new DefineException("The maxRepetitions cannot be less than 1.");
        this.minRepetitions = minRepetitions;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public Matcher<E> matcher() {
        return new Matcher<E>(this) {
            @Override
            public void match(Consumer<E> consumer) {
                List<Matcher<E>> matchers = matchers();
                List<TrackPoints<E>> trackPoints = new ArrayList<>();
                TrackPoints<E> points = new TrackPoints<>();
                trackPoints.add(points);

                List<Node<E>> nodes = new ArrayList<>();
                Node<E> node = new Node<>(consumer.state());
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
                for (int i = 0; true; i++) {
                    if (nodes.isEmpty())
                        consumer.error("");
                    try {
                        nodes = analyser.analyseMatchers(matchers, nodes, consumer.branch());
                        if (!nodes.isEmpty() && (minRepetitions == null || minRepetitions == 0 || minRepetitions <= i)) {
                            trackPoints = new ArrayList<>();
                            for (Node<E> eNode : nodes) {
                                points = eNode.getTrackPoints().clone();
                                points.push(new TrackPoint<>(eNode));
                                eNode.setTrackPoints(points);
                                trackPoints.add(points);
                            }
                            consumer.setPointBranches(trackPoints);
                        }
                        if (maxRepetitions != null && maxRepetitions >= i)
                            consumer.close();
                    } catch (MatchException e) {
                        consumer.errorAt(e.getMessage(), e.getIndex(), e.getContent());
                    }
                }
            }
        };
    }

    @Override
    public OrDefiner<E, LoopDefiner<E, T>> or() {
        return (OrDefiner<E, LoopDefiner<E, T>>) super.or();
    }

    public T end() {
        return parent;
    }
}
