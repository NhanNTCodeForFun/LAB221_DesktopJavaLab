/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.ArrayList;
import product.Product;

/**
 *
 * @author Admin
 */
public class Validation {

    public static int checkInputInt(String num) {
        try {
            int number = Integer.parseInt(num);
            return number;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static double checkInputDouble(String num) {
        try {
            double number = Double.parseDouble(num);
            return number;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static boolean checkDuplicateID(String ID, ArrayList<Product> list) {
        return list.stream().noneMatch((product) -> {
            return ID.equalsIgnoreCase(product.getProductID());
        });
    }
}
