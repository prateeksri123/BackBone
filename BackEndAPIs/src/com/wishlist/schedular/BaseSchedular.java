package com.wishlist.schedular;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
public class BaseSchedular {
   private final ScheduledExecutorService scheduler = 
      Executors.newScheduledThreadPool(1);

   public void beepForAnHour() {
       final Runnable beeper = new Runnable() {
               public void run() { System.out.println("----->>>> Start Updation");
                                   UpdateCategorySchedular ucs = new UpdateCategorySchedular();
                                   UpdateProductSchedular ups = new UpdateProductSchedular();
                                   System.out.println("----->>>> End Updation");}
           };
       final ScheduledFuture<?> beeperHandle =
           scheduler.scheduleAtFixedRate(beeper, 10, 20 * 60, TimeUnit.SECONDS);
       scheduler.schedule(new Runnable() {
               public void run() { beeperHandle.cancel(true); }
           }, 60 * 60, SECONDS);
   }
}