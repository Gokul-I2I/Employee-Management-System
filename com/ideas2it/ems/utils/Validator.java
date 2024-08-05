package com.ideas2it.ems.utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class handle the Name Validation and Age Calculation.
 * @author Gokul
 */
public class Validator {
    /**
     * Calculate age by formatted String
     *
     * @param employee date of birth (dd/mm/yyyy) format String value
     * 
     * @return Period calculated year, month, days
     */
    public static Period calculateDateOfBirth(String dateOfBirth) {
        Period calculateAge = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);
            LocalDate currentDate = LocalDate.now();
            if (birthDate.isAfter(currentDate)) {
                System.out.print(" Date of birth cannot be in the future.");
            }
            calculateAge = Period.between(birthDate, currentDate);   
        } catch (DateTimeParseException e) {
            System.out.print(" Invalid date format. Please use dd/MM/yyyy.");        
        }
	return calculateAge;
    }

    /**
     * String validation only contain Alphabets
     *
     * @param name
     *     
     * @return name is valid name or not
     */
    public static boolean isValidName(String name) {
        String regex = "^[a-zA-Z]+([ ][a-zA-Z])*$";
        return Pattern.matches(regex, name);
    }   

    /**
     * Calculate the age 
     *
     * @param employee date of birth (dd/mm/yyyy) format 
     * 
     * @return Period calculated year, month, days
     */
    public static Period calculateDateOfBirth(LocalDate dateOfBirth) {
        Period calculateAge = null;
        try {
            LocalDate currentDate = LocalDate.now();
            if (dateOfBirth.isAfter(currentDate)) {
                System.out.print(" Date of birth cannot be in the future.");
            }
            calculateAge = Period.between(dateOfBirth, currentDate);   
        } catch (DateTimeParseException e) {
            System.out.print(" Invalid date format. Please use dd/MM/yyyy.");        
        }
	return calculateAge;
    }

    /**
     * String to date convertion
     *
     * @param employeeDateOfBirth 
     *     
     * @return LocalDate : date converted
     */
    public static LocalDate dateConverter(String employeeDateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(employeeDateOfBirth, formatter);
    }
}