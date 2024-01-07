package br.com.fiap.postech.fastfood.utils.loghelper;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Builder;

@Builder
public class LogHelper {
  public static final String EVENT_TAG_KEY = "event";
  public static final String METHOD_TAG_KEY = "method";
  private final Map<String, String> tags = new ConcurrentHashMap<>();
  private Throwable throwable;
  private org.slf4j.Logger logger;

  private Optional<Throwable> getThrowable() {
    return Optional.ofNullable(this.throwable);
  }

  /**
   * atLog create LogHelper.
   *
   * @param logger for logging
   * @return LogHelper
   */
  public static LogHelper atLog(org.slf4j.Logger logger) {
    return new LogHelper(null, logger);
  }

  /**
   * withEvent tag.
   *
   * @param value for event tag
   * @return LogHelper
   */
  public LogHelper withEvent(final String value) {
    withTag(EVENT_TAG_KEY, value);
    return this;
  }

  /**
   * withMethod tag.
   *
   * @param value for method tag
   * @return LogHelper
   */
  public LogHelper withMethod(final String value) {
    withTag(METHOD_TAG_KEY, value);
    return this;
  }

  /**
   * withData tag.
   *
   * @param key for event tag
   * @param value for event tag
   * @return LogHelper
   */
  public LogHelper withData(final String key, final Object value) {
    withTag(key, value.toString());
    return this;
  }

  private String getTags() {
    StringBuilder tagsBuilder = new StringBuilder();
    this.tags.forEach(
        (key, value) -> tagsBuilder
            .append('[')
            .append(key)
            .append(':')
            .append(value)
            .append("] "));

    return tagsBuilder.toString();
  }

  private String getMessage(String message) {
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append(getTags()).append("MESSAGE: ").append(message);

    if (getThrowable().isPresent()) {
      messageBuilder.append(" STACKTRACE: ").append(getThrowable().get());
    }

    return messageBuilder.toString();
  }

  /**
   * info log.
   *
   * @param msg for log
   * @param cpf
   */
  public void info(final String msg) {
    logger.info(getMessage(msg));
  }

  /**
   * warn log.
   *
   * @param msg for log
   */
  public void warn(final String msg) {
    logger.warn(getMessage(msg));
  }

  /**
   * warn log.
   *
   * @param msg for log
   * @param throwable for log
   */
  public void warn(final String msg, Throwable throwable) {
    this.throwable = throwable;
    logger.warn(getMessage(msg));
  }

  /**
   * debug log.
   *
   * @param msg for log
   */
  public void debug(final String msg) {
    logger.debug(getMessage(msg));
  }

  /**
   * error log.
   *
   * @param msg for log
   */
  public void error(final String msg) {
    logger.error(getMessage(msg));
  }

  /**
   * error log.
   *
   * @param msg for log
   * @param throwable for log
   */
  public void error(final String msg, Throwable throwable) {
    this.throwable = throwable;
    logger.error(getMessage(msg));
  }

  private void withTag(final String key, final String value) {
    tags.put(key, value);
  }
}
