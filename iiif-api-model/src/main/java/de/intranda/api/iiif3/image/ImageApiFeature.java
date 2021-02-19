package de.intranda.api.iiif3.image;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum ImageApiFeature {

	/**
	 * The base URI of the service will redirect to the image information document.
	 */
	baseUriRedirect,
	/**
	 * The canonical image URI HTTP link header is provided on image responses.
	 */
	canonicalLinkHeader,
	/**
	 * The CORS HTTP headers are provided on all responses.
	 */
	cors,
	/**
	 * The JSON-LD media type is provided when requested.
	 */
	jsonldMediaType,
	/**
	 * The image may be rotated around the vertical axis, resulting in a left-to-right mirroring of the content.
	 */
	mirroring,
	/**
	 * The profile HTTP link header is provided on image responses.
	 */
	profileLinkHeader,
	/**
	 * Regions of the full image may be requested by percentage.
	 */
	regionByPct,
	/**
	 * Regions of the full image may be requested by pixel dimensions.
	 */
	regionByPx,
	/**
	 * A square region may be requested, where the width and height are equal to the shorter dimension of the full image.
	 */
	regionSquare,
	/**
	 * Image rotation may be requested using values other than multiples of 90 degrees.
	 */
	rotationArbitrary,
	/**
	 * Image rotation may be requested in multiples of 90 degrees.
	 */
	rotationBy90s,
	/**
	 * Image size may be requested in the form !w,h.
	 */
	sizeByConfinedWh,
	/**
	 * Image size may be requested in the form ,h.
	 */
	sizeByH,
	/**
	 * Images size may be requested in the form pct:n.
	 */
	sizeByPct,
	/**
	 * Image size may be requested in the form w,.
	 */
	sizeByW,
	/**
	 * Image size may be requested in the form w,h.
	 */
	sizeByWh,
	/**
	 * Image sizes prefixed with ^ may be requested.
	 */
	sizeUpscaling;
	
	/**
	 * @return all features that must be supported by compliance level 2 (https://iiif.io/api/image/3/level2.json)
	 */
	public static Set<ImageApiFeature> getLevel2Features() {
		return EnumSet.of(
				baseUriRedirect,
				cors,
				jsonldMediaType,
				regionByPct,
				regionByPx,
				regionSquare,
				rotationBy90s,
				sizeByConfinedWh,
				sizeByH,
				sizeByPct,
				sizeByW,
				sizeByWh
				);
	}
	
	public static Set<String> getLevel2FeaturesAsString() {
		return getLevel2Features().stream().map(ImageApiFeature::name).collect(Collectors.toSet());
	}
	
	/**
	 * @return all features supported by contentServer iiif image 3 implementation
	 */
	public static Set<ImageApiFeature> getSupportedFeatures() {
		return EnumSet.of(
				baseUriRedirect,
				cors,
				jsonldMediaType,
				profileLinkHeader,
				mirroring,
				regionByPct,
				regionByPx,
				regionSquare,
				rotationArbitrary,
				rotationBy90s,
				sizeByConfinedWh,
				sizeByH,
				sizeByPct,
				sizeByW,
				sizeByWh
				);
	}
	
	public static Set<ImageApiFeature> getExtraFeatures() {
		Set<ImageApiFeature> features = getSupportedFeatures();
		features.removeAll(getLevel2Features());
		return features;
	}


}
