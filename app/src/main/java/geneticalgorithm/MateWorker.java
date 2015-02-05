package geneticalgorithm;

import java.util.concurrent.Callable;

/**
 * * Introduction to Neural Networks with Java, 2nd Edition
 * Copyright 2008 by Heaton Research, Inc.
 * http://www.heatonresearch.com/books/java-neural-2/
 *
 * ISBN13: 978-1-60439-008-7
 * ISBN:   1-60439-008-5
 *
 * This class is released under the:
 * GNU Lesser General Public License (LGPL)
 * http://www.gnu.org/copyleft/lesser.html
 */
public class MateWorker<CHROMOSME_TYPE extends Chromosome<?, ?>>
    implements Callable<Integer> {

    private final CHROMOSME_TYPE mother;
    private final CHROMOSME_TYPE father;
    private final CHROMOSME_TYPE child1;
    private final CHROMOSME_TYPE child2;

    public MateWorker(final CHROMOSME_TYPE mother, final CHROMOSME_TYPE father,
                      final CHROMOSME_TYPE child1, final CHROMOSME_TYPE child2) {
        this.mother = mother;
        this.father = father;
        this.child1 = child1;
        this.child2 = child2;
    }

    @SuppressWarnings("unchecked")
    public Integer call() throws Exception {
        this.mother.mate((Chromosome)this.father,
                         (Chromosome)this.child1,
                         (Chromosome)this.child2);
        return null;
    }

}
