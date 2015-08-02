package visualizer.representation;

import java.io.IOException;

import visualizer.corpus.Corpus;

public class RepresentationFactory {

    public static Representation getInstance(RepresentationType type, Corpus corpus) throws IOException {
        Representation representation = null;
        if (type.equals(RepresentationType.VECTOR_SPACE_MODEL) || type.equals(RepresentationType.VECTOR_SPACE_REFERENCES)) {
            representation = new VectorSpaceRepresentation(corpus);
        } else if (type.equals(RepresentationType.LDA)) {
            representation = new LDAModel(corpus);
        }
        return representation;
    }
}
