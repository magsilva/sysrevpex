package visualizer.textprocessing;

public class PreProcessorFactory
{
	static final Class<? extends PreProcessor> DEFAULT_PREPROCESSOR = MonoliticPreprocessor.class;
	
	public static PreProcessor getInstance()
	{
		try {
			return DEFAULT_PREPROCESSOR.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not load the default pre-processor", e);
		}
	}
	
	public static BasicPreProcessor getBasicInstance()
	{
		try {
			return (BasicPreProcessor) DEFAULT_PREPROCESSOR.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not load the default pre-processor", e);
		}
	}
}
