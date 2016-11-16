package com.jagadish.testapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*public class EagerInitializedSingleton {

    private EagerInitializedSingleton() throws Exception {
    }

    private static class SingletonHelper{
           private static  EagerInitializedSingleton INSTANCE=null;
           static{

                  try {
                        INSTANCE = new EagerInitializedSingleton();
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                  }
           }


    }

    public static EagerInitializedSingleton getInstance(){
           return SingletonHelper.INSTANCE;
    }

    public static void main(String[] args) {
           ExecutorService exc = Executors.newCachedThreadPool();
           final HashMap<Integer, Integer> map = new HashMap<>();
           
           Collections.synchronizedMap(map);
           
           for(int i=0;i<100000;i++){
                  exc.submit(new Runnable() {

                        @Override
                        public void run() {
                               while(true){
                                      try {
                                             Thread.sleep(10);
                                      } catch (InterruptedException e) {
                                             e.printStackTrace();
                                      }
                                      map.put(EagerInitializedSingleton.getInstance().hashCode(), 1);
                                      //EagerInitializedSingleton.getInstance());
                                      if(map.size() != 1){
                                             System.out.println("error"+map.size()+map);
                                      }
                               }
                        }
                  });

           }
           
           exc.shutdown();
           try {
                  exc.awaitTermination(1, TimeUnit.HOURS);
           } catch (InterruptedException e) {
                  e.printStackTrace();
           }
           


    }

}

*/