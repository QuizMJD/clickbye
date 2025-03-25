package com.example.mapper.util;

import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp tiện ích cung cấp các phương thức xử lý ngày tháng cho MapStruct
 * Các phương thức được đánh dấu @Named để có thể sử dụng trong qualifiedByName của @Mapping
 */
public class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Chuyển đổi LocalDate thành String theo định dạng dd/MM/yyyy
     * 
     * @param date ngày cần định dạng
     * @return chuỗi ngày đã định dạng hoặc null nếu đầu vào null
     */
    @Named("formatDate")
    public static String formatDate(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_FORMATTER);
    }

    /**
     * Chuyển đổi String thành LocalDate
     * Xử lý chuỗi có định dạng dd/MM/yyyy
     * 
     * @param dateStr chuỗi ngày cần parse
     * @return đối tượng LocalDate hoặc null nếu đầu vào null/rỗng
     */
    @Named("parseDate")
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * Chuyển đổi LocalDateTime thành String theo định dạng dd/MM/yyyy HH:mm
     * 
     * @param dateTime thời gian cần định dạng
     * @return chuỗi thời gian đã định dạng hoặc null nếu đầu vào null
     */
    @Named("formatDateTime")
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * Chuyển đổi String thành LocalDateTime
     * Xử lý chuỗi có định dạng dd/MM/yyyy HH:mm
     * 
     * @param dateTimeStr chuỗi thời gian cần parse
     * @return đối tượng LocalDateTime hoặc null nếu đầu vào null/rỗng
     */
    @Named("parseDateTime")
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
    }
} 