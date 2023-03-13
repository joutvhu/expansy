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
                for (int i = 0; true; i++) {
                    if (nodes.isEmpty())
                        consumer.error("");
                    try {
                        nodes = analyser.analyseMatchers(matchers, nodes, consumer.branch());
                        if (!nodes.isEmpty() && (minRepetitions == null || minRepetitions == 0 || minRepetitions <= i)) {
                            cases = new ArrayList<>();
                            for (NodeImpl<E> eNode : nodes) {
                                trackPoints = eNode.getTrackPoints().clone();
                                trackPoints.push(new TrackPoint<>(eNode));
                                eNode.setTrackPoints(trackPoints);
                                cases.add(trackPoints);
                            }
                            consumer.setCases(cases);
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

    public T end() {
        return parent;
    }
}
