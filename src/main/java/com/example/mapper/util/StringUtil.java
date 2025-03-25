package com.example.mapper.util;

import org.mapstruct.Named;

public class StringUtil {
    
    @Named("formatFullName")
    public static String formatFullName(String firstName, String lastName) {
        StringBuilder sb = new StringBuilder();
        if (firstName != null && !firstName.trim().isEmpty()) {
            sb.append(firstName.trim());
        }
        
        if (lastName != null && !lastName.trim().isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(lastName.trim());
        }
        
        return sb.toString();
    }
    
    @Named("getStatusDescription")
    public static String getStatusDescription(boolean isActive) {
        return isActive ? "Hoạt động" : "Không hoạt động";
    }
} 