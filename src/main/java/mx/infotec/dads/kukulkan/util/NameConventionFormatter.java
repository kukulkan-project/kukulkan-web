/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.kukulkan.util;

/**
 * Name Conventions Formatter, it is a utility class used for convert different
 * kind of strings into a target format
 * 
 * @author Daniel Cortes Pichardo
 */
public class NameConventionFormatter {

    private NameConventionFormatter() {
    }

    /**
     * Convert to angular file Naming Convention is used in the front-end data
     * generator. Convert in camel case into Hyphens format
     * 
     * @param from
     * @return String
     */
    public static String camelCaseToHyphens(String from) {
        return camelCaseTo(from, '-');
    }

    /**
     * Convert to angular file Naming Convention is used in the front-end data
     * generator. Convert in camel case into underScore format
     * 
     * @param from
     * @return String
     */
    public static String camelCaseToUnderScore(String from) {
        return camelCaseTo(from, '_');
    }
    
    private static String camelCaseTo(String from, char character) {
        char[] wordArray = from.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char letter : wordArray) {
            if (Character.isUpperCase(letter)) {
                sb.append(character).append(Character.toLowerCase(letter));
            } else {
                sb.append(letter);
            }
        }
        return sb.toString();
    }
}
