package de.intranda.api.iiif;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class IIIFUrlResolver {

    /**
     * Regex to match any calls to images via iiif (not image information!). This includes the following capturing groups:
     * <ul>
     * <li>1: region</li>
     * <li>2: size</li>
     * <li>3: rotation</li>
     * <li>4: quality</li>
     * <li>5: output format</li>
     * </ul>
     */
    public static final String IIIF_IMAGE_PARAMS_REGEX =
            "\\/?((?:pct:)?(?:\\d+,\\d+,\\d+,\\d+)|full|square)\\/((?:pct:\\d{1,2})|!?(?:(?:\\d+)?,(?:\\d+)?)|full|max)\\/(!?-?\\d{1,3})\\/(default|bitonal|gray|color|native)\\.(jpg|png|tif|jp2|pdf)\\/?(?:\\?.*)?";
    public static final String IIIF_IMAGE_REGEX =
            "https?:\\/\\/.*\\/((?:pct:)?(?:\\d+,\\d+,\\d+,\\d+)|full|square)\\/((?:pct:\\d{1,2})|!?(?:(?:\\d+)?,(?:\\d+)?)|full|max)\\/(!?-?\\d{1,3})\\/(default|bitonal|gray|color|native)\\.(jpg|png|tif|jp2|pdf)(?:\\?.*)?";
    public static final int IIIF_IMAGE_REGEX_REGION_GROUP = 1;
    public static final int IIIF_IMAGE_REGEX_SIZE_GROUP = 2;
    public static final int IIIF_IMAGE_REGEX_ROTATION_GROUP = 3;
    public static final int IIIF_IMAGE_REGEX_QUALITY_GROUP = 4;
    public static final int IIIF_IMAGE_REGEX_FORMAT_GROUP = 5;
    public static final String IIIF_IMAGE_REQUEST_TEMPLATE = "{region}/{size}/{rotation}/{quality}.{format}";
    
    /**
     * Appends image request parameter paths to the given baseUrl
     * 
     * @param baseUrl
     * @return
     */
    public static String getIIIFImageUrl(String baseUrl, String region, String size, String rotation, String quality, String format) {
        if (StringUtils.isNotBlank(baseUrl) && !baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        StringBuilder url = new StringBuilder(baseUrl);
        url.append(region).append("/");
        url.append(size).append("/");
        url.append(rotation).append("/");
        url.append(quality).append(".");
        url.append(format);

        return url.toString();
    }
    
    /**
     * Replaces the image request parameters in an IIIF URL with the given ones
     * 
     * @param url
     * @param width
     * @param height
     * @return
     * @should replace dimensions correctly
     * @should do nothing if not iiif url
     */
    public static String getModifiedIIIFFUrl(String url, String region, String size, String rotation, String quality, String format) {
        Pattern pattern = Pattern.compile(IIIF_IMAGE_REGEX);
        //        Matcher matcher = Pattern.compile(IIIF_IMAGE_REGEX).matcher(url);
        if (url != null && pattern.matcher(url).matches()) {
            url = replaceGroup(url, region, pattern.matcher(url), IIIF_IMAGE_REGEX_REGION_GROUP);
            url = replaceGroup(url, size, pattern.matcher(url), IIIF_IMAGE_REGEX_SIZE_GROUP);
            url = replaceGroup(url, rotation, pattern.matcher(url), IIIF_IMAGE_REGEX_ROTATION_GROUP);
            url = replaceGroup(url, quality, pattern.matcher(url), IIIF_IMAGE_REGEX_QUALITY_GROUP);
            url = replaceGroup(url, format, pattern.matcher(url), IIIF_IMAGE_REGEX_FORMAT_GROUP);

        }
        return url;
    }
    
    /**
     * If the given {@code url} is a IIIF image url, then return a url up to the identifier (removing all url parts starting with the region part) if
     * no such parts exist, the original url is returned
     * 
     * @param url
     * @return the base url up to the identifier (no trailing slash)
     */
    public static String getIIIFImageBaseUrl(String url) {
        return url.replaceAll(IIIF_IMAGE_PARAMS_REGEX, "");
    }

    /**
     * Replaces a capturing group of a group already matched by the matcher with the String {@code replacement}
     * 
     * @param url
     * @param group
     * @return
     */
    private static String replaceGroup(String text, String replacement, Matcher matcher, int group) {
        if (replacement != null && matcher.find()) {
            int start = matcher.start(group);
            int end = matcher.end(group);
            if (start > -1 && end > -1) {
                return text.substring(0, start) + replacement + text.substring(end);
            }
        }
        return text;
    }

    /**
     * 
     * @param url
     * @return true if the given url conforms to a IIIF image request pattern (that is, an actual image is requested, not just image information)
     */
    public static boolean isIIIFImageUrl(String url) {
        return url != null && url.matches(IIIF_IMAGE_REGEX);
    }

    /**
     * Test whether the given url refers to a IIIF image information
     * 
     * @param url
     * @return true if the given url ends in "/info.json" which is assumed to refer to a IIIF image information
     */
    public static boolean isIIIFImageInfoUrl(String url) {
        return url != null && url.toLowerCase().endsWith("/image.info");
    }

}
