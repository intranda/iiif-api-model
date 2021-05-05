package de.intranda.api.iiif3.image;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import de.intranda.api.iiif.image.v3.ImageApiFeature;

public class ImageApiFeatureTest {

	@Test
	public void testFulfillLevel2ComplianceLevel() {
		Set<ImageApiFeature> level2Features = ImageApiFeature.getLevel2Features();
		Set<ImageApiFeature> supportedFeatures = ImageApiFeature.getSupportedFeatures();
		assertFalse(level2Features.isEmpty());
		assertFalse(supportedFeatures.isEmpty());
		
		Set<ImageApiFeature> missingFeatures = level2Features;
		missingFeatures.removeAll(supportedFeatures);
		assertTrue(missingFeatures.isEmpty());
	}

}
