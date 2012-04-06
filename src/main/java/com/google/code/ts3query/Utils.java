package com.google.code.ts3query;

public class Utils {

  private static final long MILLIS_PER_SECOND = 1000;
  private static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60;
  private static final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60;
  private static final long MILLIS_PER_DAY = MILLIS_PER_HOUR * 24;
  private static final long MILLIS_PER_WEEK = MILLIS_PER_DAY * 7;

  /**
   * Formats a duration in milliseconds into a human-readable string, e.g. "2d 11h 8m 4s".
   */
  public static String formatDuration(long duration) {
    long weeks = duration / MILLIS_PER_WEEK;
    duration -= weeks * MILLIS_PER_WEEK;

    long days = duration / MILLIS_PER_DAY;
    duration -= days * MILLIS_PER_DAY;

    long hours = duration / MILLIS_PER_HOUR;
    duration -= hours * MILLIS_PER_HOUR;

    long minutes = duration / MILLIS_PER_MINUTE;
    duration -= minutes * MILLIS_PER_MINUTE;

    long seconds = duration / MILLIS_PER_SECOND;
    duration -= seconds * MILLIS_PER_SECOND;

    // Don't skip time units, i.e. show "1w 0d 4h..." instead of "1w 4h ...".
    StringBuilder result = new StringBuilder();
    boolean dontSkip = false;
    if (weeks > 0) {
      result.append(' ').append(weeks).append('w');
      dontSkip = true;
    }
    if (dontSkip || days > 0) {
      result.append(' ').append(days).append('d');
      dontSkip = true;
    }
    if (dontSkip || hours > 0) {
      result.append(' ').append(hours).append('h');
      dontSkip = true;
    }
    if (dontSkip || minutes > 0) {
      result.append(' ').append(minutes).append('m');
      dontSkip = true;
    }
    if (dontSkip || seconds > 0) {
      result.append(' ').append(seconds).append('s');
      dontSkip = true;
    }
    return result.toString();
  }
}
