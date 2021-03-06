package priv.wmc.main.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import priv.wmc.common.constant.StaticConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Instant Deserializer（只支持默认的格式：DateUtils.COMMON_DATE_FORMAT（yyyy-MM-dd HH:mm:ss））
 *
 * @author 王敏聪
 * @date 2020-01-16 11:44:41
 */
@Slf4j
@JsonComponent
public class InstantDeserializer extends JsonDeserializer<Instant> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StaticConstants.DATE_TIME_PATTERN).withZone(ZoneId.of("Asia/Shanghai"));

    @Override
    public Instant deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String dateString = node.asText();
        try {
            return formatter.parse(dateString, Instant::from);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(dateString + "格式错误位于第" + (e.getErrorIndex()+1) +"字符，请使用正确格式：" + StaticConstants.DATE_TIME_PATTERN, e);
        }
    }

}
