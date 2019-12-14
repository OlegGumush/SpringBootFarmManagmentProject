package farm.utils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeUtils {

	public DateTimeUtils() {
	}
	
	public static LocalDateTime LocalDateTimeUtcNow() {
		return LocalDateTime.now(Clock.systemUTC());
	}
	
	public static Long EpochSecondsUtcNow() {
		return UtcToEpochSeconds(LocalDateTimeUtcNow());	
	}
	
	public static Long UtcToEpochSeconds(LocalDateTime time) {
		if (time == null) {
			return null;
		}
		return time.toEpochSecond(ZoneOffset.UTC);
	}
}
