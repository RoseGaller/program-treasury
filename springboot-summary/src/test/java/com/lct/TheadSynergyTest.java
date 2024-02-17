package com.lct;

import org.springframework.util.StringUtils;

import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class TheadSynergyTest {

    private static volatile  String stop = null;

    public static void main(String[] args) {

         class Printer {
             private final int n = 10;
             private final Semaphore s1 = new Semaphore(1);
             private final Semaphore s2 = new Semaphore(0);
             private final Semaphore s3 = new Semaphore(0);

             private void print(char content, Semaphore current, Semaphore next) {
                 while (true) {
                     if(!StringUtils.isEmpty(stop)){
                         break;
                     }
                     try {

                         Thread.sleep(1000);
                         current.acquire();
                         System.out.print(content);
                         next.release();
                         if(Thread.currentThread().getName().equals("")){
                             System.out.println("");
                         }
                     } catch (Exception e) {
                         System.out.println(e);
                     }
                 }
             }
             public void print(){
                 new Thread(() -> {print('A', s1, s2);},"a").start();
                 new Thread(() -> {print('L', s2, s3);},"b").start();
                 new Thread(() -> {print('I', s3, s1);},"c").start();
             }
         }
        Printer printer = new Printer();
        printer.print();

        Scanner scanner =  new Scanner(System.in);
        stop = scanner.nextLine();
        System.out.println("执行完成");

    }

}
