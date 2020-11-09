package ru.sapteh;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length==0){
            return;
        }
        String fileName="product.txt";
        ArrayList<Product> productList=new ArrayList<>();
        try(BufferedReader buffer= new BufferedReader(new FileReader(fileName))) {
            while (buffer.ready()){
                Product product=prodCreate(buffer.readLine());
                productList.add(product);
            }
        }
        if ("-c".equals(args[0])){
            int id=0;
            for (Product product:productList) {
                if (product.getId()>id){
                    id=product.getId();
                }
            }
            String name= args[args.length-3];
            if (name.length()>30){
                name = name.substring(0,30);
            }
            String price= args[args.length-2];
            if (price.length()>8){
                price =price.substring(0,8);
            }
            String quantity=args[args.length-1];
            if (quantity.length()>4){
                quantity=quantity.substring(0,4);
            }
            Product product1=new Product(++id,name,price,quantity);
            productList.add(product1);
        }
        try(FileWriter fw=new FileWriter(fileName)) {
            for (Product product:productList) {
                fw.write(product.toString());
                fw.write("\n");
            }
        }
    }
    public static Product prodCreate(String readLine){
        int id;
        String name;
        String price;
        String quantity;
        id=Integer.parseInt(readLine.substring(0,8).trim());
        name=readLine.substring(8,38).trim();
        price=readLine.substring(38,46).trim();
        quantity=readLine.substring(46,50).trim();
        return new Product(id,name,price,quantity);
    }
}
