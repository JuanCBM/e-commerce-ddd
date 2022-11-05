package com.inditex.hiring.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

  private static final DateTimeFormatter pointFormatter = DateTimeFormatter
      .ofPattern(Constants.STANDARD_POINT_DATE_FORMAT);

  private static final DateTimeFormatter colonFormatter = DateTimeFormatter
      .ofPattern(Constants.STANDARD_COLON_DATE_FORMAT);

  private DateUtils() {
    // Empty Constructor
  }

  public static LocalDateTime toLocalDateTime(String dateTime) {
    return LocalDateTime.parse(dateTime, pointFormatter);
  }

  public static String toStringPointDateTime(LocalDateTime localDateTime) {
    return localDateTime.format(pointFormatter);
  }

  public static String toStringColonDateTime(LocalDateTime localDateTime) {
    return localDateTime.format(colonFormatter);
  }

  public static boolean isBetweenRightOpenInterval(
      LocalDateTime toCheck,
      LocalDateTime startInterval,
      LocalDateTime endInterval) {
    return toCheck.compareTo(startInterval) >= 0 && toCheck.compareTo(endInterval) < 0;
  }

}
