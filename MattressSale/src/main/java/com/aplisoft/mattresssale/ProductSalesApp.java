package com.aplisoft.mattresssale;

import java.util.Scanner;

//  Interface 
interface IProduct {
    int GetTotalProductsSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();
}

//  Product Class 
class Product implements IProduct {

    private String[] products;
    private int[][] sales;
    private int topHighestMonth;

    public Product(String[] products, int[][] sales) {
        this.products = products;
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold();
        int count = 0;
        for (int i = 0; i < sales.length; i++) {
            count += sales[i].length;
        }
        return (double) total / count;
    }

    @Override
    public String GetMostPopularProduct() {
        int topIndex = 0;
        int topTotal = 0;
        topHighestMonth = 0;

        for (int i = 0; i < sales.length; i++) {
            int productTotal = 0;
            int highestMonth = sales[i][0];
            for (int j = 0; j < sales[i].length; j++) {
                productTotal += sales[i][j];
                if (sales[i][j] > highestMonth) {
                    highestMonth = sales[i][j];
                }
            }
            if (i == 0 || productTotal > topTotal) {
                topTotal = productTotal;
                topIndex = i;
                topHighestMonth = highestMonth;
            }
        }
        return products[topIndex] + "(" + topHighestMonth + ")";
    }
}

//  PrintProduct Class 
class PrintProduct {

    private Product product;

    public PrintProduct(Product product) {
        this.product = product;
    }

    public void PrintTotalProductsSold() {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void PrintAverageProductSold() {
        System.out.println("AVERAGE PRODUCT SALES: " + (int) product.GetAverageProductSold());
    }

    public void PrintMostPopularProduct() {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    public void PrintReport() {
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println();
        System.out.println("********************************************");
        PrintTotalProductsSold();
        PrintAverageProductSold();
        PrintMostPopularProduct();
    }
}


public class ProductSalesApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = new int[3][3];

        for (int i = 0; i < products.length; i++) {
            for (int month = 0; month < 3; month++) {
                System.out.print("Enter number of sales for month " + (month + 1) + " for " + products[i] + " mattress: ");
                sales[i][month] = input.nextInt();
            }
            System.out.println();
        }

        Product product = new Product(products, sales);
        PrintProduct print = new PrintProduct(product);
        print.PrintReport();
    }
}